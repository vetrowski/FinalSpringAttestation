package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.ProductRepository;
import com.example.springsecurityapplication.services.ProductService;
import com.example.springsecurityapplication.util.ProductErrorResponse;
import com.example.springsecurityapplication.util.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController = @Controller + @ResponseBody
@RestController
@RequestMapping("/api")
public class ApiController {

    private final ProductRepository productRepository;

    private final ProductService productService;

    @Autowired
    public ApiController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    /*@ResponseBody() // указываем, что из данного метода будет возвращаться не шаблон, а обычный текст. Таким образом, Spring не будет искать шаблон в папке templates*/
    @GetMapping("/info")
    public String getInfoApi(){
        return "Данное API предназначено для получения информации о товаре";
    }

    // Метод по получению всех продуктов
    @GetMapping("/product")
    public List<Product> getProduct(){
        // С помощью библиотеки Jackson преобразовавем Java-объекты в json
        return productRepository.findAll();

    }

    // Метод по удалению продукта по id
    @GetMapping("/product/delete/{id}")
    public void deleteProductId(@PathVariable("id") int id){
        productRepository.deleteById(id);
    }

    // Метод по получению товара по id
    @GetMapping("/product/{id}")
    public Product getProductId(@PathVariable("id") int id){
        return productService.findById(id);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handlerException(ProductNotFoundException productNotFoundException){
        ProductErrorResponse response = new ProductErrorResponse("Не удалось найти товар по указанному id");
        // NOT FOUND - статус 404 (в заголовке ответа)
        // RESPONSE - тело ответа
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
