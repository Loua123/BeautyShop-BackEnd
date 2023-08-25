package com.example.ecommercelouabackend.services;

import com.example.ecommercelouabackend.entities.Order;
import com.example.ecommercelouabackend.entities.Product;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.OrderRepo;
import com.example.ecommercelouabackend.repositories.ProductRepo;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderService implements ICurd<Order> {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    @Override
    public Order add(Order order) throws NotFoundEntityException {
        if(order != null)
            return this.orderRepo.save(order);
        throw new NotFoundEntityException("no Order found , order is null");
    }

    @Override
    public void delete(long id) {
        this.orderRepo.findById(id)
                .ifPresent(this.orderRepo::delete);
    }

    @Override
    public void update(Order order) {
        if(order != null)
            this.orderRepo.save(order);
    }

    @Override
    public Order get(long id) throws NotFoundEntityException {
        return this.orderRepo.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("could not found order with id " + id));
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepo.findAll();
    }


    @Transactional
    public boolean deleteProductFromOrder(long idOrder, long idProduct) {
        Optional<Order> orderOptional = orderRepo.findById(idOrder);
        Optional<Product> productOptional = productRepo.findById(idProduct);

        if (orderOptional.isPresent() && productOptional.isPresent()) {
            Order order = orderOptional.get();
            Product product = productOptional.get();

            order.getProductCollections().remove(product);
            product.getOrders().remove(order); // Update the bidirectional relationship

            orderRepo.save(order);
            productRepo.save(product); // Save changes to both entities

            System.out.println("Product removed from Order.");
        } else {
            System.out.println("Order or Product not found.");
        }

        return true;
    }



    public List<Order> getOrderbyuserid(long userid)
    {
        return this.orderRepo.findByUserId(userid);
    }
}
