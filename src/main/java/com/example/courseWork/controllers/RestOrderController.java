package com.example.courseWork.controllers;

import com.example.courseWork.entity.Order;
import com.example.courseWork.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class RestOrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity makeOrder(@RequestBody Order order) {
        boolean orderResult = orderService.makeOrder(order);
        if (orderResult == true) {
            return ResponseEntity.ok("Success");
        }
        else {
            return ResponseEntity.ok("Error");
        }
    }

}
