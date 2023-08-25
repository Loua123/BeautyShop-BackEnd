package com.example.ecommercelouabackend.services.interfaces;

import com.example.ecommercelouabackend.dto.Mail;
import com.example.ecommercelouabackend.entities.Store;
import com.example.ecommercelouabackend.entities.User;

public interface EmailService {
    public void sendCodeByMail(Mail mail);
    public void sendActivationCodeByMail(Mail mail);
    public void ResendActivationCodeByMail(Mail mail);
    public void sendConfirmationStore(User user, Store store);
    public void sendCancelStore(User user, Store store,String reasonForRejection);

}
