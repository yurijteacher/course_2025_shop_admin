package ua.com.kneu.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.kneu.course.entity.Orders;
import ua.com.kneu.course.entity.ProductHasOrder;
import ua.com.kneu.course.service.OrderService;
import ua.com.kneu.course.service.ProductHasOrderService;

import java.util.List;

@Controller
public class OrderManagerController {

    private final OrderService orderService;

    private final ProductHasOrderService productHasOrderService;

    public OrderManagerController(OrderService orderService, ProductHasOrderService productHasOrderService) {
        this.orderService = orderService;
        this.productHasOrderService = productHasOrderService;
    }

    @GetMapping("/manager/orders")
    public String getOrderPages(Model model) {

        model.addAttribute("orders", orderService.getOrdersPages());

        return "orders_manager";
    }

    @GetMapping("/manager/order/{id}")
    public String getViewOrderPage(@PathVariable("id") Orders order,
                                   Model model) {

        List<ProductHasOrder> productHasOrderList =  productHasOrderService.getProductHasOrdersByOrderId(order);
        model.addAttribute("order",order);

        double values = 0;
        for (ProductHasOrder productHasOrder : productHasOrderList) {
           values +=  productHasOrder.getQuantity()*productHasOrder.getProduct().getPrice().doubleValue();
        }

        model.addAttribute("value", values);

        model.addAttribute("productHasOrderList", productHasOrderList);

        return "order_manager";
    }


}
