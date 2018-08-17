package edu.northeastern.cs5200.orderDetail;

import edu.northeastern.cs5200.item.Item;
import edu.northeastern.cs5200.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    List<OrderDetail> findAllOrderDetails() {
        return (List<OrderDetail>) orderDetailRepository.findAll();
    }

    OrderDetail findOrderDetailById(Long orderDetailId) {
        Optional<OrderDetail> data = orderDetailRepository.findById(orderDetailId);
        return data.orElse(null);
    }

    OrderDetail updateOrderDetail(Long orderDetailId, OrderDetail newOrderDetail) throws Exception {
        Optional<OrderDetail> data = orderDetailRepository.findById(orderDetailId);
        if (data.isPresent()) {
            OrderDetail orderDetail = data.get();
            orderDetail.setItem(newOrderDetail.getItem());
            orderDetail.setOrder(newOrderDetail.getOrder());
            orderDetail.setQuantity(newOrderDetail.getQuantity());
            return orderDetailRepository.save(orderDetail);
        }
        throw new Exception("No orderDetail found with id = " + orderDetailId);
    }

    void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }

    public Order findOrder(Long orderDetailId) {
        OrderDetail orderDetail = findOrderDetailById(orderDetailId);
        return orderDetail.getOrder();
    }

    public Item findItem(Long orderDetailId) {
        OrderDetail orderDetail = findOrderDetailById(orderDetailId);
        return orderDetail.getItem();
    }

}
