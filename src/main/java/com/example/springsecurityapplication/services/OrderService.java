package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.enumm.Status;
import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Удаление заказа
    @Transactional
    public void deleteOrder(int id){
        orderRepository.deleteById(id);
    }

    // Получение заказа
    public Order getOrderId(int id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    // Изменение статуса заказа
    @Transactional
    public void updateStatus1(Order order) {
        order.setStatus(Status.Оформлен);
        orderRepository.save(order);
    }
    @Transactional
    public void updateStatus2(Order order) {
        order.setStatus(Status.Принят);
        orderRepository.save(order);
    }
    @Transactional
    public void updateStatus3(Order order) {
        order.setStatus(Status.Получен);
        orderRepository.save(order);
    }
    @Transactional
    public void updateStatus4(Order order) {
        order.setStatus(Status.Отменен);
        orderRepository.save(order);
    }

    @Transactional
    public List<Order> findByLastFourCharacters(String str){
        List<Order> orders = orderRepository.findByLastFourCharacters(str);
        return orders;
    }

    public Object geAllOrders() {
        return null;
    }

}
