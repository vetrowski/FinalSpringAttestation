package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.services.ProductService;
import com.example.springsecurityapplication.util.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductValidator productValidator;

    private final ProductService productService;

    private final CategoryRepository categoryRepository;

    @Autowired
    public SellerController(ProductValidator productValidator, ProductService productService, CategoryRepository categoryRepository) {
        this.productValidator = productValidator;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    // Метод по отображению главной страницы продавца с выводом товаров
    @GetMapping()
    public String seller(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        String role = personDetails.getPerson().getRole();
        if (role.equals("ROLE_USER")){
            return "redirect:/home";
        }
        model.addAttribute("products", productService.getAllProduct());
        return "seller/seller";
    }

    // Метод по отображению формы добавления
    @GetMapping("/product/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "product/addProduct";
    }

    // Метод по добавлению объекта с формы в таблицу product
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one")MultipartFile file_one, @RequestParam("file_two")MultipartFile file_two, @RequestParam("file_three")MultipartFile file_three, @RequestParam("file_four")MultipartFile file_four, @RequestParam("file_five")MultipartFile file_five) throws IOException {

        productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()){
            return "product/addProduct";
        }

        // Проверка на пустоту файла
        if (file_one != null){
            // Директория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной директории по пути не существует
            if (!uploadDir.exists()){
                // Тогда создаем ее
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неизменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename - наименование файла с формы
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if (file_two != null){
            // Директория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной директории по пути не существует
            if (!uploadDir.exists()){
                // Тогда создаем ек
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неизменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename - наименование файла с формы
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if (file_three != null){
            // Директория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной директории по пути не существует
            if (!uploadDir.exists()){
                // Тогда создаем ек
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неизменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename - наименование файла с формы
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if (file_four != null){
            // Директория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной директории по пути не существует
            if (!uploadDir.exists()){
                // Тогда создаем ек
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неизменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename - наименование файла с формы
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if (file_five != null){
            // Директория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной директории по пути не существует
            if (!uploadDir.exists()){
                // Тогда создаем ек
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неизменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename - наименование файла с формы
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        productService.saveProduct(product);
        return "redirect:/seller";
    }

    // Метод по удалению товара по id
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/seller";
    }

    // Метод по получению товара по id и отображение шаблона редактирования
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("editProduct", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("editProduct") Product product, @PathVariable("id") int id){
        productService.updateProduct(id, product);
        return "redirect:/seller";
    }


}
