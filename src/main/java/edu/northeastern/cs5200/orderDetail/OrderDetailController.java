package edu.northeastern.cs5200.orderDetail;

import edu.northeastern.cs5200.item.Item;
import edu.northeastern.cs5200.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping("/api/orderDetail")
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.createOrderDetail(orderDetail);
    }

    @GetMapping("/api/orderDetail")
    public List<OrderDetail> findAllOrderDetails() {
        return orderDetailService.findAllOrderDetails();
    }

    @GetMapping("/api/orderDetail/{orderDetailId}")
    public OrderDetail findOrderDetailById(@PathVariable("orderDetailId") Long orderDetailId) throws Exception {
        return orderDetailService.findOrderDetailById(orderDetailId);
    }

    @PutMapping("/api/orderDetail/{orderDetailId}")
    public OrderDetail updateOrderDetail(@PathVariable("orderDetailId") Long orderDetailId,
                                         @RequestBody OrderDetail newOrderDetail) throws Exception {
        return orderDetailService.updateOrderDetail(orderDetailId, newOrderDetail);
    }

    @DeleteMapping("/api/orderDetail/{orderDetailId}")
    public void deleteOrderDetail(@PathVariable("orderDetailId") Long id) {
        orderDetailService.deleteOrderDetail(id);
    }

    @GetMapping("/api/orderDetail/{orderDetailId}/order")
    public Order findOrder(@PathVariable("orderDetailId") Long orderDetailId) {
        return orderDetailService.findOrder(orderDetailId);
    }

    @GetMapping("/api/orderDetail/{orderDetailId}/item")
    public Item findItem(@PathVariable("orderDetailId") Long orderDetailId) {
        return orderDetailService.findItem(orderDetailId);
    }
}
