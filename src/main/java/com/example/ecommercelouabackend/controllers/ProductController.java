package com.example.ecommercelouabackend.controllers;

import com.example.ecommercelouabackend.dto.AddproductRequest;
import com.example.ecommercelouabackend.entities.Product;
import com.example.ecommercelouabackend.entities.Store;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.services.FileUploadService;
import com.example.ecommercelouabackend.services.ProductService;
import com.example.ecommercelouabackend.services.StoreService;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ICurd<Product> productICurd;
    private final FileUploadService fileUploadService;
    private final ProductService productService;
    private final StoreService storeService;
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody AddproductRequest request) throws NotFoundEntityException {

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategories(request.getCategories());
        product.setSoucat(request.getSoucat());
        product.setImage(request.getImage());
        product.setPrice(request.getPrice());
        Store store= storeService.get(request.getIdstore());
        product.setStore(store);
        store.getProducts().add(product);
        productService.add(product);
        return product;
    }
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            fileUploadService.uploadImage(file);
            redirectAttributes.addFlashAttribute("message", "File uploaded successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Failed to upload the file.");
            // Handle or log the exception as needed
        }

        return "file uploaded successfully";
    }
    @GetMapping("/getProductById")
    public ResponseEntity<Product> getProductById(@RequestParam long idProduct) throws NotFoundEntityException {
        return ResponseEntity.ok(this.productICurd.get(idProduct));
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productICurd.getAll());
    }
    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        this.productICurd.update(product);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/deleteProduct/{idProduct}")
    public ResponseEntity<String> deleteProduct(@PathVariable long idProduct) {
        this.productICurd.delete(idProduct);
        return ResponseEntity.ok("product with id " + idProduct + " is deleted");
    }

    @GetMapping("/getProductByStore/{storeId}")
    public List<Product> getProductByStore(@PathVariable long storeId) throws NotFoundEntityException {
        return this.productService.getAllProductsByIdUser(storeId);
    }
    @GetMapping("/getProductByID/{id}")
    public Product getProductByID(@PathVariable long id) throws NotFoundEntityException {
        return this.productService.get(id);
    }
}
