package com.posidex;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ScrumMasterApplicationTests {

    public static void main(String[] args) {
        // Replace these with your email credentials and OTP recipient email
        String senderEmail = "akhilvsvs1999@gmail.com";
        String senderPassword = "akhilbasss";
        String recipientEmail = "akhilchinnu420@gmail.com";

        // Generate OTP
        String otp = generateOTP();

        // Send OTP via email
        sendOTPEmail(senderEmail, senderPassword, recipientEmail, otp);
    }

    private static String generateOTP() {
        // Generate a 6-digit OTP
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return Integer.toString(otpValue);
    }

    private static void sendOTPEmail(String senderEmail, String senderPassword, String recipientEmail, String otp) {
        // Set up mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a session with the email credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set the email subject and content
            message.setSubject("Your OTP");
            message.setText("Your OTP is: " + otp);

            // Send the email
            Transport.send(message);

            System.out.println("OTP sent successfully to " + recipientEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
