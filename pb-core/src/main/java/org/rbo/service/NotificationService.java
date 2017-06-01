package org.rbo.service;

import org.rbo.model.User;

/**
 * Notification service
 */
public interface NotificationService {


    void send(String to, String subject, String content, boolean isMultipart, boolean isHtml);

    void sendActivation(User user);

    void sendCreation(User user);

    void sendPasswordReset(User user);
}
