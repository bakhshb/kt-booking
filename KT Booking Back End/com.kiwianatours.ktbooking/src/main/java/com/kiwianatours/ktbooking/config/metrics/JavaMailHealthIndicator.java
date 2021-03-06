package com.kiwianatours.ktbooking.config.metrics;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.util.Assert;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

/**
 * SpringBoot Actuator HealthIndicator check for JavaMail.
 */
public class JavaMailHealthIndicator extends AbstractHealthIndicator {

    private final Logger log = LoggerFactory.getLogger(JavaMailHealthIndicator.class);
    
    private SendGrid sendGridSender;
  
    public JavaMailHealthIndicator(SendGrid sendGridSender) {
        Assert.notNull(sendGridSender, "javaMailSender must not be null");
        this.sendGridSender = sendGridSender;
    }
    
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        log.debug("Initializing JavaMail health indicator");
		try {
			String to = "recipient@domain.com";
			String from = "test@yourdomain.com";
			SendGrid.Email email = new SendGrid.Email();
			email.addTo(to);
			email.setFrom(from);
			email.setSubject("test");
			email.setText("test body");

			SendGrid.Response response = sendGridSender.send(email);
			log.debug("Sent e-mail to Test Doamin '{}'!", to);
			log.debug("Sent e-mail Response '{}'!", response.getMessage());
			
			JSONObject json = new JSONObject(response.getMessage());
			if (json.opt("message").equals("success")) {
				builder.up();
			} else {
				builder.down();
			}

		} catch (SendGridException e) {
			log.debug("Cannot connect to e-mail server.", e);
			builder.down(e);
		}
    }
}
