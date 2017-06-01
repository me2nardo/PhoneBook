package org.rbo.service.impl;

import org.apache.commons.lang3.CharEncoding;
import org.rbo.config.properties.MailProperties;
import org.rbo.model.User;
import org.rbo.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * Mail notification
 */
@Service
public class MailNotification implements NotificationService {

    private final Logger LOG = LoggerFactory.getLogger(MailNotification.class);

    private static final String USER = "user";

    private static final String BASE_URL = "baseUrl";

    private final MailProperties mailProperties;

    private final JavaMailSender javaMailSender;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;

    public MailNotification(MailProperties mailProperties,
                            JavaMailSender javaMailSender,
                            MessageSource messageSource,
                            SpringTemplateEngine templateEngine) {
        this.mailProperties = mailProperties;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
    }

    @Async
    @Override
    public void send(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        LOG.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(mailProperties.getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            LOG.debug("Sent e-mail to User '{}'", to);
        } catch (Exception e) {
            LOG.warn("E-mail could not be sent to user '{}'", to, e);
        }
    }
    @Async
    @Override
    public void sendActivation(User user) {
        LOG.debug("Sending activation e-mail to '{}'", user.getEmail());
        Locale locale = Locale.ENGLISH; //TODO:: Create custom user locales
        Context context = new Context();
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, mailProperties.getBaseUrl());
        String content = templateEngine.process("activationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null,locale);
        send(user.getEmail(), subject, content, false, true);
    }

    @Async
    @Override
    public void sendCreation(User user) {
        LOG.debug("Sending creation e-mail to '{}'", user.getEmail());
       // Locale locale = Locale.forLanguageTag(user.getLangKey());
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, mailProperties.getBaseUrl());
        String content = templateEngine.process("creationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null, locale);
        send(user.getEmail(), subject, content, false, true);
    }

    @Async
    @Override
    public void sendPasswordReset(User user) {
        LOG.debug("Sending password reset e-mail to '{}'", user.getEmail());
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, mailProperties.getBaseUrl());
        String content = templateEngine.process("passwordResetEmail", context);
        String subject = messageSource.getMessage("email.reset.title", null, locale);
        send(user.getEmail(), subject, content, false, true);
    }
}
