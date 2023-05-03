package com.example.courseWork.service;

import com.example.courseWork.entity.Order;
import com.example.courseWork.entity.Toy;
import com.example.courseWork.repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private ToyRepository toyRepository;
    @Autowired
    private final EmailService emailSenderService;

    public OrderService(EmailService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public boolean makeOrder(Order order) {
        List<Integer> listToRecount = new ArrayList<>(order.getOrder_goods());
        boolean recount_Result = toyRepository.recountGoods(listToRecount);
        if (recount_Result == false) {
            return false;
        }
        else {
            // Отправка email покупателю с чеком
            String customer_message = "Благодарим за заказ на сайте toy.ru.\nВаш заказ:\n";
            int totalCost = 0;
            for(int i = 0; i < order.getOrder_goods().size(); i++) {
                Map<String, String> mapToGetToy = Map.of("id", order.getOrder_goods().get(i).toString());
                Toy reposToy = toyRepository.getToy(mapToGetToy).get(0);
                customer_message = customer_message + "Товар: " + reposToy.getName() + " | цена - " + reposToy.getCost() + "\n";
                totalCost += reposToy.getCost();
            }
            customer_message = customer_message + "Итоговая цена: " + totalCost;

            emailSenderService.sendEmail(
                    order.getEmail_to(),
                    "Заказ на сайте toy.ru",
                    customer_message);



            // Отправка email на почту магазина с информацией о заказе
            String shop_message = "Информация о заказе:\n";
            shop_message = shop_message + "Имя: " + order.getName() + "\n";
            shop_message = shop_message + "Фамилия: " + order.getLast() + "\n";
            shop_message = shop_message + "Почта: " + order.getEmail_to() + "\n";
            shop_message = shop_message + "Телефон: " + order.getNumber() + "\n";
            shop_message = shop_message + "Адрес: " + order.getAddress() + "\n";
            shop_message = shop_message + "Комментарий: " + order.getComment() + "\n";
            shop_message = shop_message + "Товары: " + order.getOrder_goods().toString() + "\n";

            emailSenderService.sendEmail("odnorazovaya33@yandex.ru",
                    "Заказ на сайте toy.ru",
                    shop_message);

            return true;
        }
    }
}
