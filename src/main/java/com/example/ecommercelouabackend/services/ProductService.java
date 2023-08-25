package com.example.ecommercelouabackend.services;

import com.example.ecommercelouabackend.entities.Product;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.ProductRepo;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService implements ICurd<Product> {

    private final ProductRepo productRepo;

    @Override
    public Product add(Product product) throws NotFoundEntityException {
        if(product != null)
            return this.productRepo.save(product);

        throw new NotFoundEntityException("no Product found , product is null");

    }

    @Override
    public void delete(long id) {
        this.productRepo.findById(id)
                .ifPresent(this.productRepo::delete);

    }

    @Override
    public void update(Product product) {
        if(product != null)
            this.productRepo.save(product);
    }

    @Override
    public Product get(long id) throws NotFoundEntityException {
        return this.productRepo.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("could not found product with id " + id));
    }

    @Override
    public List<Product> getAll() {
        return this.productRepo.findAll();
    }

    public List<Product> getAllProductsByIdUser(long storeId)
    {
        return this.productRepo.findProductsByStoreId(storeId);
    }


}
