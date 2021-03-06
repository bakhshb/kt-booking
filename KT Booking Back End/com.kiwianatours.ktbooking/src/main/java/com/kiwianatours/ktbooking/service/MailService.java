package com.kiwianatours.ktbooking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.util.Locale;

/**
 * Service for sending e-mails.
 * <p/>
 * <p>
 * We use the @Async annotation to send e-mails asynchronously.
 * </p>
 */
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    @Inject
    private Environment env;

    @Inject
    private SendGrid sendGridSender;


    @Inject
    private MessageSource messageSource;

    /**
     * System default email address that sends the e-mails.
     */
    private String from;
    private String fromName;

    @PostConstruct
    public void init() {
        this.from = env.getProperty("spring.mail.from");
        this.fromName = env.getProperty("spring.mail.fromName");
    }

    @Async
	public void sendEmail(String to, String subject, String content,
			boolean isMultipart, boolean isHtml) {
		log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",	isMultipart, isHtml, to, subject, content);

		// Prepare message using a Spring helper
		SendGrid.Email email = new SendGrid.Email();
		email.addTo(to);
		email.setFrom(this.from);
		email.setFromName(this.fromName);
		email.setSubject(subject);
		email.setHtml(content);

		try {
			SendGrid.Response response = sendGridSender.send(email);
			log.debug("Sent e-mail to User '{}'!", to);
			log.debug("Sent e-mail Response '{}'!", response.getMessage());
		} catch (SendGridException e) {
			log.warn("E-mail could not be sent to user '{}', exception is: {}",
					to, e.getMessage());
		}
	}

    @Async
    public void sendActivationEmail(final String email, String content, Locale locale) {
        log.debug("Sending activation e-mail to '{}'", email);
        String subject = messageSource.getMessage("email.activation.title", null, locale);
        sendEmail(email, subject, content, false, true);
    }
    
    @Async
    public void sendBookingApprovalEmail (final String email, String content, Locale locale){
    	log.debug("Sending booking e-mail to '{}'", email);
    	String subject = messageSource.getMessage("email.booking.title", null, locale);
    	sendEmail(email, subject, content, false, true);
    }
}
