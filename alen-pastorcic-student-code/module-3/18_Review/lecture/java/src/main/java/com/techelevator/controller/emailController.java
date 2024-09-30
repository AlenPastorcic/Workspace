package com.techelevator.controller;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class emailController {

    public static void main(String[] args) {
        final String username = "charcode.cookout@gmail.com";
        final String password = "mmqm xwfw qvwm arml";



        if (username == null || username.isEmpty()) {
            System.out.println("Username is not set!");
        }
        if (password == null || password.isEmpty()) {
            System.out.println("Password is not set!");
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("charcode.cookout@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("jeremias.bonet1@gmail.com"));
            message.setSubject("Invite Notice");
            message.setText("Hello,\n\nThis is your invite notice.\n\nBest regards,\nYour Company");

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}














