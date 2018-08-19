package edu.northeastern.cs5200.order;

import edu.northeastern.cs5200.item.Item;
import edu.northeastern.cs5200.item.ItemService;
import edu.northeastern.cs5200.orderDetail.OrderDetail;
import edu.northeastern.cs5200.orderDetail.OrderDetailRepository;
import edu.northeastern.cs5200.person.trainer.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ItemService itemService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderDetailRepository orderDetailRepository,
                        ItemService itemService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.itemService = itemService;
    }

    Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    List<Order> findAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    Order findOrderById(Long orderId) {
        Optional<Order> data = orderRepository.findById(orderId);
        return data.orElse(null);
    }

    Order updateOrder(Long orderId, Order newOrder) throws Exception {
        Optional<Order> data = orderRepository.findById(orderId);
        if (data.isPresent()) {
            Order order = data.get();
            order.setCost(newOrder.getCost());
            order.setTrainer(newOrder.getTrainer());
            return orderRepository.save(order);
        }
        throw new Exception("No order found with id = " + orderId);
    }

    void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Trainer findOrderCreator(Long orderId) {
        Order order = findOrderById(orderId);
        return order.getTrainer();
    }

    public Order addOrderDetails(Long orderId,
                                 Long orderDetailId) {
        Order order = findOrderById(orderId);
        Optional<OrderDetail> optional = orderDetailRepository.findById(orderDetailId);
        OrderDetail orderDetail = optional.orElse(null);

        order.addOrderDetail(orderDetail);
        return orderRepository.save(order);
    }

    public Iterable<OrderDetail> findOrderDetails(Long orderId) {
        Order order = findOrderById(orderId);
        return order.getOrderDetails();
    }

    public Order createOrderFromBundle(List<Item> items) {
        Order order = new Order();
        order = orderRepository.save(order);
        Double cost = 0.0;

        for (Item item : items) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(item.getQuantity());
            orderDetail = orderDetailRepository.save(orderDetail);
            itemService.addOrderDetails(item.getId(), orderDetail.getId());
            addOrderDetails(order.getId(), orderDetail.getId());
            cost += item.getQuantity() * item.getPrice();
        }
        order.setCost(cost);
        order = orderRepository.save(order);
        return order;
    }
}
