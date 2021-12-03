package projectI.demo.server.controllers;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import projectI.demo.server.config.MyConstants;
import projectI.demo.server.mapping.ProductMapping;
import projectI.demo.server.models.Product;
import projectI.demo.server.repositories.ProductRepository;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(ProductMapping.PRODUCT_GET_MAPPING)
    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    @PostMapping(ProductMapping.PRODUCT_POST_MAPPING)
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product){
        //System.out.println(user.getFirstName());
        productRepository.save(product);
    }
}
