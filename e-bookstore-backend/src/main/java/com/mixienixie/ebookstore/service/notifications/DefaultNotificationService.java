package com.mixienixie.ebookstore.service.notifications;

import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.repo.core.PublishingHouseRepository;
import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.repo.core.entity.OrderEntity;
import com.mixienixie.ebookstore.repo.core.entity.OrderItemEntity;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import com.mixienixie.ebookstore.service.AuthorizationService;
import com.mixienixie.ebookstore.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Default implementation of the Notification Service
 *
 * #TODO switch form using maps to POJOs/DTOs
 * @author ndjordjieski
 */
@Service
@RequiredArgsConstructor
public class DefaultNotificationService implements NotificationService{

    /** Publishing House Service */
    private final PublishingHouseRepository publishingHouseRepository;
    /** Authorization Service */
    private final AuthorizationService authorizationService;

    /** EMail Sender */
    private final JavaMailSender emailSender;

    /** Flag for enabling sending of mails */
    @Value("${ebookstore.mail.enabled}") @Setter
    private boolean mailSendingEnabled;


    /**
     * {@inheritDoc}
     */
    @Override
    public void sendBookPurchaseNotification(OrderEntity orderEntity){
        Objects.requireNonNull(orderEntity);
        Objects.requireNonNull(orderEntity.getOrderItems());

        Map<Long, Set<OrderItemEntity>> booksByPublishingHouse = this.getBooksByPublishingHouse(orderEntity);

        List<SimpleMailMessage> publishingHouseMessages = this.getPublishingHouseMailMessages(booksByPublishingHouse, orderEntity.getAddress());
        SimpleMailMessage userMessage = this.getUserMessage(orderEntity.getOrderItems(), orderEntity.getAddress());

        this.sendMessages(publishingHouseMessages);
        this.sendMessage(userMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendRepresentativeRegisteredNotification(PublishingHouseEntity publishingHouseEntity, UserEntity userEntity){
        Objects.requireNonNull(publishingHouseEntity);
        Objects.requireNonNull(userEntity);

        String representativeRegisteredBody = String.format(NotificationService.EMAIL_BODY_REPRESENTATIVE_REGISTERED,
                userEntity.getFirstName(), userEntity.getLastName(), userEntity.getUsername());
        representativeRegisteredBody += "\n\n" + NotificationService.EMAIL_BODY_REPRESENTATIVE_REGISTERED_OUTRO;

        SimpleMailMessage representativeMessage = this.createMessage(publishingHouseEntity.getEmail(),
                NotificationService.EMAIL_TITLE_REPRESENTATIVE_REGISTERED, representativeRegisteredBody);

        this.sendMessage(representativeMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMessages(List<SimpleMailMessage> messages){
        if(CollectionUtils.isEmpty(messages)){
            throw new ValidationException("Messages must not be null or empty!");
        }

        // #TODO validate if they are any null messages before trying to send to avoid partial message delivery
        messages.forEach(this::sendMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMessage(SimpleMailMessage message){
        Objects.requireNonNull(message);

        if(this.mailSendingEnabled){
            this.emailSender.send(message);
        }
    }

    /**
     * Parses a map books and their quantities to a map of publishing house ids and books and their quantities.
     * @param orderEntity Order containing individual books and quantities
     * @return Map of Publishing House Ids and their books and quantities
     */
    private Map<Long, Set<OrderItemEntity>> getBooksByPublishingHouse(OrderEntity orderEntity){
        Map<Long, Set<OrderItemEntity>> booksByPublishingHouse = new HashMap<>();
        orderEntity.getOrderItems().forEach(orderItemEntity -> {
            Long publishingHouseId = orderItemEntity.getBook().getPublishingHouse().getId();

            booksByPublishingHouse.computeIfAbsent(publishingHouseId, k -> new HashSet<>());
            booksByPublishingHouse.get(publishingHouseId).add(orderItemEntity);
        });

        return booksByPublishingHouse;
    }

    /**
     * Generates a simple mail message notification for the publishing house for the passed publishing house id and the map of books
     * and their quantities, and the recipient address
     * @param publishingHouseId publishing house id
     * @param orderItems Set of order items
     * @param address recipient address
     * @return EMail message for the book purchases for the publishing house
     */
    private SimpleMailMessage generateEmailForPublishingHouse(Long publishingHouseId, Set<OrderItemEntity> orderItems, String address){
        PublishingHouseEntity publishingHouseEntity = this.publishingHouseRepository.findById(publishingHouseId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find publishing house with id: " + publishingHouseId));
        UserEntity userEntity = this.authorizationService.getAuthenticatedUser();

        String emailTitle = String.format(NotificationService.EMAIL_TITLE_PUBLISHING_HOUSE_BOOK_PURCHASE,
                userEntity.getFirstName(), userEntity.getLastName(), userEntity.getUsername());
        String emailBodyIntro = String.format(NotificationService.EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_INTRO,
                userEntity.getFirstName(), userEntity.getLastName(), userEntity.getUsername());

        StringBuilder emailBody = new StringBuilder(emailBodyIntro).append("\n\n");

        String bookEntries = this.generateBodyForBooksAndQuantities(orderItems, NotificationService.EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_BOOK);
        emailBody.append(bookEntries);

        address = String.format(NotificationService.EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_ADDRESS, address);
        emailBody.append("\n").append(address).
                append("\n\n").append(NotificationService.EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_OUTRO);

        return this.createMessage(publishingHouseEntity.getEmail(), emailTitle, emailBody.toString());
    }

    /**
     * Generates the mail notifications to be sent to the publishing houses for the book purchases
     * @param ordersByPublishingHouse Map of publisher house ids and related order items
     * @param address recipient address
     * @return List of book purchase mail notifications for publishing houses
     */
    private List<SimpleMailMessage> getPublishingHouseMailMessages(Map<Long, Set<OrderItemEntity>> ordersByPublishingHouse, String address){
        List<SimpleMailMessage> publishingHouseMessages = new ArrayList<>();

        ordersByPublishingHouse.keySet().forEach(publishingHouseId -> {
            SimpleMailMessage publishingHouseMessage = this.generateEmailForPublishingHouse(publishingHouseId,
                    ordersByPublishingHouse.get(publishingHouseId), address);
            publishingHouseMessages.add(publishingHouseMessage);
        });

        return publishingHouseMessages;
    }

    /**
     * Generates the authorized user notification for his book purchase
     * @param orderItems Order Items
     * @param address recipient address
     * @return EMail message for user book purchase
     */
    private SimpleMailMessage getUserMessage(Set<OrderItemEntity> orderItems, String address){
        UserEntity userEntity = this.authorizationService.getAuthenticatedUser();

        String emailTitle = String.format(NotificationService.EMAIL_TITLE_USER_BOOK_PURCHASE, new Date());

        StringBuilder emailBody = new StringBuilder(EMAIL_BODY_USER_BOOK_PURCHASE_INTRO).append("\n\n");

        String bookEntries = this.generateBodyForBooksAndQuantities(orderItems, NotificationService.EMAIL_BODY_USER_BOOK_PURCHASE_BOOK);
        emailBody.append(bookEntries);

        address = String.format(NotificationService.EMAIL_BODY_USER_BOOK_PURCHASE_ADDRESS, address);
        emailBody.append("\n").append(address)
                .append("\n\n").append(NotificationService.EMAIL_BODY_USER_BOOK_PURCHASE_OUTRO);

        return this.createMessage(userEntity.getUsername(), emailTitle, emailBody.toString());
    }

    /**
     * Generates the email body part containing information about purchased books and their quantities, based on the passed template
     * @param orderItems Set of order items
     * @param template Template to use to generate the body part
     * @return Email body part with book purchase entries
     */
    private String generateBodyForBooksAndQuantities(Set<OrderItemEntity> orderItems, String template){
        StringBuilder emailBody = new StringBuilder();
        orderItems.forEach(orderItemEntity ->  {
            BookEntity bookEntity = orderItemEntity.getBook();

            int quantity = orderItemEntity.getQuantity();
            double priceForBooks = bookEntity.getPrice() * quantity;

            String bookEntry = String.format(template, bookEntity.getTitle(),
                    bookEntity.getAuthor().getFirstName(), bookEntity.getAuthor().getLastName(), quantity, bookEntity.getPrice(), priceForBooks);

            emailBody.append(bookEntry).append("\n");
        });

        return emailBody.toString();
    }

    /**
     * Creates a simple mail message
     * @param to recipient
     * @param subject subject
     * @param text body
     * @return SimpleMailMessage
     */
    private SimpleMailMessage createMessage(String to, String subject, String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        return mailMessage;
    }
}
