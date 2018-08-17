package edu.northeastern.cs5200.item;

import edu.northeastern.cs5200.orderDetail.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/api/item")
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @GetMapping("/api/item")
    public List<Item> findAllItems() {
        return itemService.findAllItems();
    }

    @GetMapping("/api/item/{itemId}")
    public Item findItemById(@PathVariable("itemId") Long itemId) throws Exception {
        return itemService.findItemById(itemId);
    }

    @PutMapping("/api/item/{itemId}")
    public Item updateItem(@PathVariable("itemId") Long itemId,
                           @RequestBody Item newItem) throws Exception {
        return itemService.updateItem(itemId, newItem);
    }

    @DeleteMapping("/api/item/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long id) {
        itemService.deleteItem(id);
    }

    @PutMapping("/api/item/{itemId}/orderDetail/{orderDetailId}")
    public Item addOrderDetails(@PathVariable("itemId") Long itemId,
                                @PathVariable("orderDetailId") Long orderDetailId) {
        return itemService.addOrderDetails(itemId, orderDetailId);
    }

    @GetMapping("/api/item/{itemId}/orderDetail")
    public Iterable<OrderDetail> findOrderDetails(@PathVariable("itemId") Long itemId) {
        return itemService.findOrderDetails(itemId);
    }
}
