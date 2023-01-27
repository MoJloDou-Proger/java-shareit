package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ItemDto addItem(@RequestHeader("X-Later-User-Id") Long userId, ItemDto item) {
        return itemService.addItem(userId, item);
    }

    @PatchMapping("/{itemId}")
    public ItemDto editItem(@RequestHeader("X-Later-User-Id") Long userId, @PathVariable Long itemId, ItemDto item) {
        return itemService.editItem(userId, itemId, item);
    }

    @GetMapping("/{itemId}")
    public ItemDto findItemById(@RequestHeader("X-Later-User-Id") Long userId, @PathVariable Long itemId) {
        return itemService.findItemById(userId, itemId);
    }

    @GetMapping
    public List<ItemDto> searchOwnerItems(@RequestHeader("X-Later-User-Id") Long userId) {
        return itemService.searchOwnerItems(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItemsByText(@RequestHeader("X-Later-User-Id") Long userId, @RequestParam String text) {
        return itemService.searchItemsByText(userId, text);
    }
}
