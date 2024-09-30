package com.techelevator.services;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService {

    public static String getContent(String recipientName,String eventTitle, String date, String location, String invitationCode, String hostName, String contactInfo){
        String emailInvitation = "Dear %s,\n\n" +
                "I hope this email finds you well. We are excited to invite you to our upcoming Cookout event, " +
                "where we will be gathering for an afternoon of delicious food, great company, and memorable moments.\n\n" +
                "Event Details:\n\n" +
                "Event: %s\n" +
                "Date: %s\n" +
                "Location: %s\n" +
                "Invitation Code: %s (Please add invitation in our app to access to the event!)\n" +

                "\nThis event is a wonderful opportunity to unwind, enjoy a variety of grilled delicacies, " +
                "and connect with fellow attendees in a relaxed setting. We encourage you to bring your family and friends along to share in the fun.\n\n" +
                "The event will be hosted by %s, and we look forward to seeing you there!\n\n" +
                "Best regards,\n" +
                "%s\n" +
                "%s";

        return String.format(
                emailInvitation,
                recipientName, eventTitle, date, location, invitationCode, hostName, hostName, contactInfo
        );
    }

    public static void sendEmail(String content, String email) {
        final String username = "charcode.cookout@gmail.com";
        final String password = "mmqm xwfw qvwm arml";

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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("You're Invited: Join Us for a Cookout Event!");
            message.setText(content);
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}














