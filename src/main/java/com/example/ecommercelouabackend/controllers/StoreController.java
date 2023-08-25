package com.example.ecommercelouabackend.controllers;
import com.example.ecommercelouabackend.dto.ConfirmStoreRequest;
import com.example.ecommercelouabackend.dto.RefuseStoreRequest;
import com.example.ecommercelouabackend.dto.addStoreRequest;
import com.example.ecommercelouabackend.entities.Role;
import com.example.ecommercelouabackend.entities.Store;
import com.example.ecommercelouabackend.entities.User;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.StoreRepo;
import com.example.ecommercelouabackend.repositories.UserRepo;
import com.example.ecommercelouabackend.services.StoreService;
import com.example.ecommercelouabackend.services.UserService;
import com.example.ecommercelouabackend.services.interfaces.EmailService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class StoreController {

    private final EmailService emailService ;
    private final StoreRepo storeRepo;
    private final StoreService storeService;
    private final UserService userService;
    private final UserRepo userRepo;

    public StoreController(EmailService emailService, StoreRepo storeRepo, StoreService storeService, UserService userService, UserRepo userRepo) {
        this.emailService = emailService;
        this.storeRepo = storeRepo;
        this.storeService = storeService;
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @PostMapping("/addStore")
    public int addStore(@RequestBody addStoreRequest store) throws NotFoundEntityException {
        if (storeService.addStore(store)) {return 1;}
            return 0;
    }
    @GetMapping("/getAllStores")
    public List<Store> getAllStores() {
        return this.storeRepo.findAll();
    }
    @GetMapping("/getAllStoresbyuser/{userId}")
    public List<Store> getAllStoresbyuser(@PathVariable long userId) {
        return this.storeRepo.findByUserId(userId);
    }
    @PostMapping("/ConfirmStore")
    public int confirmStore(@RequestBody ConfirmStoreRequest request ) throws NotFoundEntityException {
        User user = this.userService.get(request.getIduser());
        Store store = this.storeService.get(request.getIdstore());
        if (user!=null && store!=null)
        {
            user.setRole(Role.SELLER);
            store.setStatus(true);
            this.userRepo.save(user);
            this.storeRepo.save(store);
            this.emailService.sendConfirmationStore(user, store);
            return 1;
        }
        return 0;
    }
    @PostMapping("/RefuseStore")
    public int RefuseStore(@RequestBody RefuseStoreRequest request) throws NotFoundEntityException {
        User user = this.userService.get(request.getIduser());
        Store store = this.storeService.get(request.getIdstore());
        if (user!=null && store!=null)
        {
            user.setRole(Role.CLIENT);
            store.setStatus(true);
            this.userRepo.save(user);
            this.storeRepo.delete(store);
            this.emailService.sendCancelStore(user, store,request.getMotif());
            return 1;
        }
        return 0;
    }
}
