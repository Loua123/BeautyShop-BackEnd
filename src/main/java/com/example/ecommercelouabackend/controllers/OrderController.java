package com.example.ecommercelouabackend.controllers;


import com.example.ecommercelouabackend.dto.OrderDetailsDTO;
import com.example.ecommercelouabackend.entities.Order;
import com.example.ecommercelouabackend.entities.Product;
import com.example.ecommercelouabackend.entities.User;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.UserRepo;
import com.example.ecommercelouabackend.services.OrderService;
import com.example.ecommercelouabackend.services.ProductService;
import com.example.ecommercelouabackend.services.UserService;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final ICurd<Order> orderICurd;
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;
    private final UserRepo userRepo;

    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody OrderDetailsDTO order) throws NotFoundEntityException {
        float total = 0;
        Order orderToAdd = new Order();
        orderToAdd.setDate(new Date());
        orderToAdd.setPrice(0);
        orderToAdd.setQuantity(0);
        orderToAdd.setStatus(false);
        User user = userRepo.findById(order.getUserid()).get();
        orderToAdd.setUser(user);
        Collection<Product> productCollections = new ArrayList<>();
        for (int i = 0; i < order.getOrderDetails().size(); i++) {
            Long idproduct = order.getOrderDetails().get(i).getProductId();
            Product product = productService.get(idproduct);
            for (int y = 0; y < order.getOrderDetails().get(i).getQuantity(); y++) {
                System.out.println(product.getName());
                total += Float.parseFloat(product.getPrice());
                Product newProductInstance = productService.get(idproduct);
                productCollections.add(newProductInstance);
            }
        }
        orderToAdd.setProductCollections(productCollections);
        orderToAdd.setPrice(total);
        orderToAdd.setQuantity(productCollections.size());
        orderToAdd.setId_seller(order.getIdseller());
        Order savedOrder = orderService.add(orderToAdd);
        for (Product product : productCollections) {
            product.getOrders().add(savedOrder);
            productService.add(product);
        }
        return savedOrder;
    }



    @GetMapping("/getOrderById/{idOrder}")
    public Order getOrderById(@PathVariable long idOrder) throws NotFoundEntityException {
        Order order = this.orderICurd.get(idOrder);
        // Load the productCollections collection manually
        Hibernate.initialize(order.getProductCollections());
        return order;
    }
    @GetMapping("/getOrderByIduser/{iduser}")
    public List<Order> getOrderByIduser(@PathVariable long iduser) {
        return this.orderService.getOrderbyuserid(iduser);
    }
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(this.orderICurd.getAll());
    }


    @PutMapping("/updateOrder")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        this.orderICurd.update(order);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/deleteOrder/{idOrder}")
    public boolean deleteOrder(@PathVariable long idOrder) {
        this.orderICurd.delete(idOrder);
        return true;
    }
    @PostMapping("/removeProductFromOrder/{idOrder}/{idProduct}")
    public boolean removeProductFromOrder(@PathVariable long idOrder,@PathVariable long idProduct)
    {
        return this.orderService.deleteProductFromOrder(idOrder,idProduct);
    }
}
