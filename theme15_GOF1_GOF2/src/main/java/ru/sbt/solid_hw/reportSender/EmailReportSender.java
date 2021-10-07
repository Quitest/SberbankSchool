package ru.sbt.solid_hw.reportSender;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailReportSender implements ReportSender {



    public void sendReport(String report, String recipients) {
        try {
            // now when the report is built we need to send it to the recipients list
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            // we're going to use google mail to send this message
            mailSender.setHost("mail.google.com"); //FIXME хардкод конфига. Обычно надо выносить в отдельные методы и/или конфиг файлы
            // construct the message
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            // setting message text, last parameter 'true' says that it is HTML format
            helper.setText(report, true);                     //FIXME хардкод конфига. Обычно надо выносить в отдельные методы и/или конфиг файлы
            helper.setSubject("Monthly department salary report"); //FIXME хардкод конфига. Обычно надо выносить в отдельные методы и/или конфиг файлы
            // send the message
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();        //FIXME Не удачная обработка -исключения бросай раньше, обрабатывай позже.
        }
    }
}
