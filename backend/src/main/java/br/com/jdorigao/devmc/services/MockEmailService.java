package br.com.jdorigao.devmc.services;

import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulating email sending...");
        LOG.info(msg.toString());
        LOG.info("Email successfully sent.");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Simulating email html sending...");
        LOG.info(msg.toString());
        LOG.info("Email html successfully sent.");
    }
}
