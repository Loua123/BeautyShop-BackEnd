package com.example.ecommercelouabackend.services;

import com.example.ecommercelouabackend.dto.Mail;
import com.example.ecommercelouabackend.entities.Store;
import com.example.ecommercelouabackend.entities.User;
import com.example.ecommercelouabackend.services.interfaces.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImp implements EmailService {

    private final JavaMailSender javaMailSender;
    public EmailServiceImp(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Override
    @Async
    public void sendCodeByMail(Mail mail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setFrom("contact.beautyshoploua@gmail.com");
            helper.setTo(mail.getTo());
            helper.setSubject("Recuperation du compte");

            String htmlContent = "<h3>Réinitialisation de mot de passe</h3>"
                    + "<p>Cher utilisateur,</p>"
                    + "<p>Vous avez demandé à réinitialiser votre mot de passe. Veuillez utiliser le code suivant pour procéder :</p>"
                    + "<h2 style=\"background-color: #f4f4f4; padding: 10px; display: inline-block;\">"
                    + mail.getCode() + "</h2>"
                    + "<p>Si vous n'avez pas fait cette demande, vous pouvez ignorer cet e-mail en toute sécurité.</p>"
                    + "<p>Cordialement,<br>Beauty Shop Loua</p>";

            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Gérer les exceptions de manière appropriée
        }
    }

    @Override
    public void sendActivationCodeByMail(Mail mail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setFrom("contact.beautyshoploua@gmail.com");
            helper.setTo(mail.getTo());
            helper.setSubject("Activation de votre compte Beauty Shop");

            String htmlContent = "<h3>Bienvenue sur la plateforme Beauty Shop Loua</h3>"
                    + "<p>Cher utilisateur,</p>"
                    + "<p>Nous sommes ravis de vous accueillir sur notre plateforme. Voici le code d'activation pour votre compte :</p>"
                    + "<h2 style=\"background-color: #f4f4f4; padding: 10px; display: inline-block;\">"
                    + mail.getCode() + "</h2>"
                    + "<p>Utilisez ce code pour activer votre compte et commencer à profiter de nos services.</p>"
                    + "<p>Si vous n'avez pas créé de compte sur notre plateforme, veuillez ignorer cet e-mail.</p>"
                    + "<p>Cordialement,<br>L'équipe Beauty Shop Loua</p>";

            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Gérer les exceptions de manière appropriée
        }
    }

    @Override
    public void ResendActivationCodeByMail(Mail mail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setFrom("contact.beautyshoploua@gmail.com");
            helper.setTo(mail.getTo());
            helper.setSubject("Code d'activation");

            String htmlContent = "<h3>Bienvenue sur la plateforme Beauty Shop Loua</h3>"
                    + "<p>Cher utilisateur,</p>"
                    + "<p>Nous sommes ravis de vous accueillir sur notre plateforme. Voici le code d'activation pour votre compte :</p>"
                    + "<h2 style=\"background-color: #f4f4f4; padding: 10px; display: inline-block;\">"
                    + mail.getCode() + "</h2>"
                    + "<p>Utilisez ce code pour activer votre compte et commencer à profiter de nos services.</p>"
                    + "<p>Si vous n'avez pas créé de compte sur notre plateforme, veuillez ignorer cet e-mail.</p>"
                    + "<p>Si vous souhaitez recevoir un nouveau code de vérification, veuillez cliquer sur le lien suivant :</p>"
                    + "<a href=\"#\">Renvoyer le code de vérification pour le compte</a>"
                    + "<p>Cordialement,<br>L'équipe Beauty Shop Loua</p>";


            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Gérer les exceptions de manière appropriée
        }
    }

    @Override
    public void sendConfirmationStore(User user, Store store) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setFrom("contact.beautyshoploua@gmail.com");
            helper.setTo(user.getUsername());
            helper.setSubject("Demande boutique " + store.getName() + " acceptée");
            String htmlContent = "<h3>Bienvenue sur la plateforme Beauty Shop Loua</h3>"
                    + "<p>Merci pour votre demande pour devenir un vendeur chez Beauty Shop. Votre demande pour la boutique <strong>" + store.getName() + "</strong> a été acceptée avec succès. Maintenant, vous pouvez accéder à votre profil pour voir votre boutique activée et vous pouvez poster des produits. Bonne travail !</p>"
                    + "<p>Cordialement,<br>L'équipe Beauty Shop Loua</p>";
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Gérer les exceptions de manière appropriée
        }
    }

    @Override
    public void sendCancelStore(User user, Store store,String reasonForRejection) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setFrom("contact.beautyshoploua@gmail.com");
            helper.setTo(user.getUsername());
            helper.setSubject("Demande boutique " + store.getName() + " acceptée");
            String htmlContent = "<h3>Bienvenue sur la plateforme Beauty Shop Loua</h3>"
                    + "<p>Nous regrettons de vous informer que votre demande pour la boutique <strong>" + store.getName() + "</strong> n'a pas été acceptée par l'administrateur pour la raison suivante:</p>"
                    + "<p><strong>Motif de refus:</strong> " + reasonForRejection + "</p>"
                    + "<p>Veuillez vérifier les informations fournies et resoumettre votre demande ultérieurement. Si vous avez des questions, n'hésitez pas à contacter notre équipe d'assistance.</p>"
                    + "<p>Cordialement,<br>L'équipe Beauty Shop Loua</p>";
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Gérer les exceptions de manière appropriée
        }
    }
}
