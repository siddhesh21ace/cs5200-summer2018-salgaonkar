package edu.northeastern.cs5200.item;

import edu.northeastern.cs5200.orderDetail.OrderDetail;
import edu.northeastern.cs5200.orderDetail.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, OrderDetailRepository orderDetailRepository) {
        this.itemRepository = itemRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    Item createItem(Item item) {
        return itemRepository.save(item);
    }

    List<Item> findAllItems() {
        return (List<Item>) itemRepository.findAll();
    }

    Item findItemById(Long itemId) {
        Optional<Item> data = itemRepository.findById(itemId);
        return data.orElse(null);
    }

    Item updateItem(Long itemId, Item newItem) throws Exception {
        Optional<Item> data = itemRepository.findById(itemId);
        if (data.isPresent()) {
            Item item = data.get();
            item.setItemType(newItem.getItemType());
            item.setName(newItem.getName());
            item.setPrice(newItem.getPrice());
            item.setTotal(newItem.getTotal());
            return itemRepository.save(item);
        }
        throw new Exception("No item found with id = " + itemId);
    }

    void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item addOrderDetails(Long itemId,
                                Long orderDetailId) {
        Item item = findItemById(itemId);
        Optional<OrderDetail> optional = orderDetailRepository.findById(orderDetailId);
        OrderDetail orderDetail = optional.orElse(null);

        item.addOrderDetail(orderDetail);
        return itemRepository.save(item);
    }

    public Iterable<OrderDetail> findOrderDetails(Long itemId) {
        Item item = findItemById(itemId);
        return item.getOrderDetails();
    }
}
