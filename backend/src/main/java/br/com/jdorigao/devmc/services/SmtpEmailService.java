package br.com.jdorigao.devmc.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Email sending...");
        mailSender.send(msg);
        LOG.info("Email successfully sent.");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Email html sending...");
        javaMailSender.send(msg);
        LOG.info("Email html successfully sent.");
    }
}
