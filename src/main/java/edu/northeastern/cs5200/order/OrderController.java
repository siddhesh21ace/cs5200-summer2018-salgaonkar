package edu.northeastern.cs5200.order;

import edu.northeastern.cs5200.item.Item;
import edu.northeastern.cs5200.orderDetail.OrderDetail;
import edu.northeastern.cs5200.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/order")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/api/order")
    public List<Order> findAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/api/order/{orderId}")
    public Order findOrderById(@PathVariable("orderId") Long orderId) throws Exception {
        return orderService.findOrderById(orderId);
    }

    @PutMapping("/api/order/{orderId}")
    public Order updateOrder(@PathVariable("orderId") Long orderId,
                             @RequestBody Order newOrder) throws Exception {
        return orderService.updateOrder(orderId, newOrder);
    }

    @DeleteMapping("/api/order/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/api/order/{orderId}/trainer")
    public Person findOrderCreator(@PathVariable("orderId") Long orderId) {
        return orderService.findOrderCreator(orderId);
    }

    @PutMapping("/api/order/{orderId}/orderDetail/{orderDetailId}")
    public Order addOrderDetails(@PathVariable("orderId") Long orderId,
                                 @PathVariable("orderDetailId") Long orderDetailId) {
        return orderService.addOrderDetails(orderId, orderDetailId);
    }

    @GetMapping("/api/order/{orderId}/orderDetail")
    public Iterable<OrderDetail> findOrderDetails(@PathVariable("orderId") Long orderId) {
        return orderService.findOrderDetails(orderId);
    }

    @PostMapping("/api/order/bundle")
    public Order createOrderFromBundle(@RequestBody List<Item> items) {
        return orderService.createOrderFromBundle(items);
    }

}
