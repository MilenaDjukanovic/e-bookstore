package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;
import java.util.Map;

/**
 * Service for sending various types of notifications
 * @author ndjordjieski
 */
public interface NotificationService{

    /** Title for email sent to publishing house when their book has been purchased */
    String EMAIL_TITLE_PUBLISHING_HOUSE_BOOK_PURCHASE = "Books Purchased by %s %s (%s)";
    /** Intro for email body for email sent to publishing house when their book has been purchased */
    String EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_INTRO = "The following books have been purchased by %s %s (%s) :";
    /** Book entry email body section for purchased books for email sent to publishing house when their book has been purchased */
    String EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_BOOK = "\tBook: %s (Author: %s %s) - Quantity: %s - Price per book: %s - Total Price: %s";
    /** Recipient address part of email body for email sent to publishing house when their book has been purchased */
    String EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_ADDRESS = "The delivery address is: %s";
    /** Outro for email body for email sent to publishing house when their book has been purchased */
    String EMAIL_BODY_PUBLISHING_HOUSE_BOOK_PURCHASE_OUTRO = "Best Regards,\n Mixie Nixie Bookstore";

    /** Title for email sent to user for book purchase */
    String EMAIL_TITLE_USER_BOOK_PURCHASE = "Purchase receipt for %s";
    /** Intro for email body for email sent to user for book purchase */
    String EMAIL_BODY_USER_BOOK_PURCHASE_INTRO = "Your purchase for the following books has been realized: ";
    /** Book entry email body section for email sent to user for book purchase */
    String EMAIL_BODY_USER_BOOK_PURCHASE_BOOK = "\tBook: %s (Author: %s %s) - Quantity: %s - Price per book: %s - Total Price: %s";
    /** Recipient address for email body for email sent to user for book purchase */
    String EMAIL_BODY_USER_BOOK_PURCHASE_ADDRESS = "Your books will be delivered to the following address: %s";
    /** Outro for email body for email sent to user for book purchase */
    String EMAIL_BODY_USER_BOOK_PURCHASE_OUTRO = "Best Regards,\n Mixie Nixie Bookstore";

    /** Title for email sent for representative registration */
    String EMAIL_TITLE_REPRESENTATIVE_REGISTERED = "A representative has been registered for your publishing house";
    /** Body for email sent for representative registration */
    String EMAIL_BODY_REPRESENTATIVE_REGISTERED = "%s %s (%s) has been registered as your representative.";
    /** Outro for email body for email sent for representative registration */
    String EMAIL_BODY_REPRESENTATIVE_REGISTERED_OUTRO = "Best Regards,\n Mixie Nixie Bookstore";

    /**
     * Sends a book purchase email for the provided books and quantities to the related publishing houses, for the
     * currently authorized user and an email to the authorized user that his purchase has been finalized
     * @param bookPurchase Map of books and their quantities for purchase
     */
    void sendBookPurchaseNotification(Map<BookEntity, Integer> bookPurchase, String address);

    void sendRepresentativeRegisteredNotification(PublishingHouseEntity publishingHouseEntity, UserEntity userEntity);

    /**
     * Sends the provided list of mail messages
     * @param messages List of SimpleMailMessages to send
     */
    void sendMessages(List<SimpleMailMessage> messages);

    /**
     * Sends the provided mail message
     * @param message SimpleMailMessage to send
     */
    void sendMessage(SimpleMailMessage message);
}
