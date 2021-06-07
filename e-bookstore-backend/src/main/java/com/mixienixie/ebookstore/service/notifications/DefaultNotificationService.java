package com.mixienixie.ebookstore.service.notifications;

import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import com.mixienixie.ebookstore.service.AuthorizationService;
import com.mixienixie.ebookstore.service.NotificationService;
import com.mixienixie.ebookstore.service.PublishingHouseService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private final PublishingHouseService publishingHouseService;
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
    public void sendBookPurchaseNotification(Map<BookEntity, Integer> bookPurchase, String address){
        Map<Long, Map<BookEntity, Integer>> publishingHouseIdBooksMap = this.getPublishingHouseIdAndBookMap(bookPurchase);

        List<SimpleMailMessage> publishingHouseMessages = this.getPublishingHouseMailMessages(publishingHouseIdBooksMap, address);
        SimpleMailMessage userMessage = this.getUserMessage(bookPurchase, address);

        this.sendMessages(publishingHouseMessages);
        this.sendMessage(userMessage);
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
     * @param bookPurchase Map of book entities and their quantities
     * @return Map of Publishing House Ids and their books and quantities
     */
    private Map<Long, Map<BookEntity, Integer>> getPublishingHouseIdAndBookMap(Map<BookEntity, Integer> bookPurchase){
        Map<Long, Map<BookEntity, Integer>> publishingHouseIdBooksMap = new HashMap<>();
        bookPurchase.keySet().forEach(bookEntity -> {
            Long publishingHouseId = bookEntity.getPublishingHouse().getId();
            Map<BookEntity, Integer> booksAndQuantity = publishingHouseIdBooksMap.computeIfAbsent(publishingHouseId, k -> new HashMap<>());

            int quantity = bookPurchase.get(bookEntity);
            if(booksAndQuantity.containsKey(bookEntity)){
                quantity += booksAndQuantity.get(bookEntity);
            }

            booksAndQuantity.put(bookEntity, quantity);
        });

        return publishingHouseIdBooksMap;
    }

    /**
     * Generates a simple mail message notification for the publishing house for the passed publishing house id and the map of books
     * and their quantities, and the recipient address
     * @param publishingHouseId publishing house id
     * @param booksAndQuantity books for the publishing house and their quantities
     * @param address recipient address
     * @return EMail message for the book purchases for the publishing house
     */
    private SimpleMailMessage generateEmailForPublishingHouse(Long publishingHouseId, Map<BookEntity, Integer> booksAndQuantity, String address){
        PublishingHouseEntity publishingHouseEntity = this.publishingHouseService.findById(publishingHouseId);
        UserEntity userEntity = this.authorizationService.getAuthenticatedUser();

        String emailTitle = String.format(NotificationService.EMAIL_TITLE_PUBLISHING_HOUSE_BOOK_PURCHASE,
                userEntity.getFirstName(), userEntity.getLastName(), userEntity.getUsername());
        String emailBodyIntro = String.format(NotificationService.EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_INTRO,
                userEntity.getFirstName(), userEntity.getLastName(), userEntity.getUsername());

        StringBuilder emailBody = new StringBuilder(emailBodyIntro).append("\n\n");

        String bookEntries = this.generateBodyForBooksAndQuantities(booksAndQuantity, NotificationService.EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_BOOK);
        emailBody.append(bookEntries);

        address = String.format(NotificationService.EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_ADDRESS, address);
        emailBody.append("\n").append(address).
                append("\n\n").append(NotificationService.EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_OUTRO);

        return this.createMessage(publishingHouseEntity.getEmail(), emailTitle, emailBody.toString());
    }

    /**
     * Generates the mail notifications to be sent to the publishing houses for the book purchases
     * @param publishingHouseIdBooksMap Map of publishing house Ids and their book purchases and quantities
     * @param address recipient address
     * @return List of book purchase mail notifications for publishing houses
     */
    private List<SimpleMailMessage> getPublishingHouseMailMessages(Map<Long, Map<BookEntity, Integer>> publishingHouseIdBooksMap, String address){
        List<SimpleMailMessage> publishingHouseMessages = new ArrayList<>();

        publishingHouseIdBooksMap.keySet().forEach(publishingHouseId -> {
            SimpleMailMessage publishingHouseMessage = this.generateEmailForPublishingHouse(publishingHouseId,
                    publishingHouseIdBooksMap.get(publishingHouseId), address);

            publishingHouseMessages.add(publishingHouseMessage);
        });

        return publishingHouseMessages;
    }

    /**
     * Generates the authorized user notification for his book purchase
     * @param bookPurchase Map of books and quantities for purchase
     * @param address recipient address
     * @return EMail message for user book purchase
     */
    private SimpleMailMessage getUserMessage(Map<BookEntity, Integer> bookPurchase, String address){
        UserEntity userEntity = this.authorizationService.getAuthenticatedUser();

        String emailTitle = String.format(NotificationService.EMAIL_TITLE_USER_BOOK_PURCHASE, new Date());

        StringBuilder emailBody = new StringBuilder(EMAIL_BODY_USER_BOOK_PURCHASE_INTRO).append("\n\n");

        String bookEntries = this.generateBodyForBooksAndQuantities(bookPurchase, NotificationService.EMAIL_BODY_USER_BOOK_PURCHASE_BOOK);
        emailBody.append(bookEntries);

        address = String.format(NotificationService.EMAIL_BODY_USER_BOOK_PURCHASE_ADDRESS, address);
        emailBody.append("\n").append(address)
                .append("\n\n").append(NotificationService.EMAIL_BODY_USER_BOOK_PURCHASE_OUTRO);

        return this.createMessage(userEntity.getUsername(), emailTitle, emailBody.toString());
    }

    /**
     * Generates the email body part containing information about purchased books and their quantities, based on the passed template
     * @param bookPurchase Map of books purchased and their quantities
     * @param template Template to use to generate the body part
     * @return Email body part with book purchase entries
     */
    private String generateBodyForBooksAndQuantities(Map<BookEntity, Integer> bookPurchase, String template){
        StringBuilder emailBody = new StringBuilder();
        bookPurchase.keySet().forEach(bookEntity -> {
            int quantity = bookPurchase.get(bookEntity);
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
