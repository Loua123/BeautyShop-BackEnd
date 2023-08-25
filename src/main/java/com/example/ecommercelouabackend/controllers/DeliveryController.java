package com.example.ecommercelouabackend.controllers;


import com.example.ecommercelouabackend.entities.Delivery;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final ICurd<Delivery> deliveryICurd;

    @PostMapping("/addDelivery")
    public ResponseEntity<Delivery> addDelivery(@RequestBody Delivery delivery) throws NotFoundEntityException {
        return ResponseEntity.ok(this.deliveryICurd.add(delivery));
    }

    @GetMapping("/getDeliveryById")
    public ResponseEntity<Delivery> getDeliveryById(@RequestParam long idDelivery) throws NotFoundEntityException {
        return ResponseEntity.ok(this.deliveryICurd.get(idDelivery));
    }

    @GetMapping("/getAllDeliveries")
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        return ResponseEntity.ok(this.deliveryICurd.getAll());
    }

    @PutMapping("/updateDelivery")
    public ResponseEntity<Delivery> updateDelivery(@RequestBody Delivery delivery) {
        this.deliveryICurd.update(delivery);
        return ResponseEntity.ok(delivery);
    }

    @DeleteMapping("/deleteDelivery")
    public ResponseEntity<String> deleteDelivery(@RequestParam long idDelivery) {
        this.deliveryICurd.delete(idDelivery);
        return ResponseEntity.ok("delivery with id " + idDelivery + " is deleted");
    }
}
