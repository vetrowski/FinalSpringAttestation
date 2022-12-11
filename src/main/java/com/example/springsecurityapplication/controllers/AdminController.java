package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.repositories.OrderRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.services.OrderService;
import com.example.springsecurityapplication.services.PersonService;
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
@RequestMapping("/admin")
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductValidator productValidator;

    private final ProductService productService;

    private final CategoryRepository categoryRepository;

    private final PersonService personService;

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    @Autowired
    public AdminController(ProductValidator productValidator, ProductService productService, CategoryRepository categoryRepository, PersonService personService, OrderRepository orderRepository, OrderService orderService) {
        this.productValidator = productValidator;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.personService = personService;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('')")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('')")

    // Метод по отображению главной страницы администратора с выводом товаров
    @GetMapping()
    public String admin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        String role = personDetails.getPerson().getRole();
        if (role.equals("ROLE_USER")) {
            return "redirect:/home";
        }
        model.addAttribute("products", productService.getAllProduct());
        return "admin/admin";
    }

    // Метод по отображению формы добавления
    @GetMapping("/product/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "product/addProduct";
    }

    // Метод по добавлению объекта с формы в таблицу product
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one") MultipartFile file_one, @RequestParam("file_two") MultipartFile file_two, @RequestParam("file_three") MultipartFile file_three, @RequestParam("file_four") MultipartFile file_four, @RequestParam("file_five") MultipartFile file_five) throws IOException {

        productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            return "product/addProduct";
        }

        // Проверка на пустоту файла
        if (file_one != null) {
            // Директория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной директории по пути не существует
            if (!uploadDir.exists()) {
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
        if (file_two != null) {
            // Директория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной директории по пути не существует
            if (!uploadDir.exists()) {
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
        if (file_three != null) {
            // Директория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной директории по пути не существует
            if (!uploadDir.exists()) {
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
        if (file_four != null) {
            // Директория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной директории по пути не существует
            if (!uploadDir.exists()) {
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
        if (file_five != null) {
            // Директория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной директории по пути не существует
            if (!uploadDir.exists()) {
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
        return "redirect:/admin";
    }

    // Метод по удалению товара по id
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    // Метод по получению товара по id и отображение шаблона редактирования
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("editProduct", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("editProduct") Product product, @PathVariable("id") int id) {
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }

    // Метод возвращает страницу с выводом пользователей и кладет объект пользователя в модель
    @GetMapping("/person")
    public String person(Model model) {
        ;
        model.addAttribute("person", personService.getAllPerson());
        return "person/person";
    }

    // Метод возвращает страницу с подробной информацией о пользователе
    @GetMapping("/person/info/{id}")
    public String infoPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getPersonById(id));
        return "person/personInfo";
    }

    // Метод возвращает страницу с формой редактирования пользователя и помещает в модель объект редактируемого пользователя по id
    @GetMapping("/person/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("editPerson", personService.getPersonById(id));
        return "person/editPerson";
    }


    // Метод принимает объект с формы и обновляет пользователя
    @PostMapping("/person/edit/{id}")
    public String editPerson(@ModelAttribute("editPerson") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "person/editPerson";
        }
        personService.updatePerson(id, person);
        return "redirect:/admin/person";
    }

    // Метод по удалению пользователей
    @GetMapping("/person/delete/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return "redirect:/admin/person";
    }

    // Метод по нажатию на кнопку поиска и сортировки и отображение шаблона
    @GetMapping("/person/sorting_and_searching_and_filters")
    public String sorting_and_searching_and_filters() {
        return "person/SortingAndSearchingAndFilters";
    }

    @PostMapping("/person/sorting_and_searching_and_filters")
    public String sorting_and_searching_and_filters(@RequestParam("SortingAndSearchingAndFiltersOptions") String sortingAndSearchingAndFiltersOptions, @RequestParam("value") String value, Model model) {
        if (sortingAndSearchingAndFiltersOptions.equals("login")) {
            model.addAttribute("person", personService.getPersonLogin(value));
        } else if (sortingAndSearchingAndFiltersOptions.equals("role")) {
            model.addAttribute("person", personService.getPersonRole(value));
        }
        return "person/SortingAndSearchingAndFilters";
    }

    // Метод для вывода всего списка заказов
    @GetMapping("/orderList")
    public String showOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "admin/orderList";
    }

    // Удаление заказа
    @GetMapping("/product/orderList/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") int id) {
        orderService.deleteOrder(id);
        return "redirect:/admin/orderList";
    }

    // Изменение статуса заказа
    @GetMapping("/product/orderList/editStatus1/{id}")
    public String editOrderStatus1(@PathVariable() int id) {
        Order orderStatus = orderService.getOrderId(id);
        orderService.updateStatus1(orderStatus);
        return "redirect:/admin/orderList";
    }

    @GetMapping("/product/orderList/editStatus2/{id}")
    public String editOrderStatus2(@PathVariable() int id) {
        Order orderStatus = orderService.getOrderId(id);
        orderService.updateStatus2(orderStatus);
        return "redirect:/admin/orderList";
    }

    @GetMapping("/product/orderList/editStatus3/{id}")
    public String editOrderStatus3(@PathVariable() int id) {
        Order orderStatus = orderService.getOrderId(id);
        orderService.updateStatus3(orderStatus);
        return "redirect:/admin/orderList";
    }

    @GetMapping("/product/orderList/editStatus4/{id}")
    public String editOrderStatus4(@PathVariable() int id) {
        Order orderStatus = orderService.getOrderId(id);
        orderService.updateStatus4(orderStatus);
        return "redirect:/admin/orderList";
    }

    // Поиск по номеру заказа
    @PostMapping("/orderList/search")
    public String orderNumberSearch(@RequestParam("search") String search, Model model) {
        if (search.length()>4) {
            search = search.substring(search.length() - 4);
        }
        model.addAttribute("orders", orderService.findByLastFourCharacters(search));
        return "admin/searchOrder";
    }
}
