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
    private static final String REQUEST_HEADER = "X-Sharer-User-Id";
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ItemDto addItem(@RequestHeader(REQUEST_HEADER) Long userId, @RequestBody ItemDto item) {
        return itemService.addItem(userId, item);
    }

    @PatchMapping("/{itemId}")
    public ItemDto editItem(@RequestHeader(REQUEST_HEADER) Long userId, @PathVariable Long itemId, @RequestBody ItemDto item) {
        return itemService.editItem(userId, itemId, item);
    }

    @GetMapping("/{itemId}")
    public ItemDto findItemById(@RequestHeader(REQUEST_HEADER) Long userId, @PathVariable Long itemId) {
        return itemService.findItemById(userId, itemId);
    }

    @GetMapping
    public List<ItemDto> searchOwnerItems(@RequestHeader(REQUEST_HEADER) Long userId) {
        return itemService.searchOwnerItems(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItemsByText(@RequestHeader(REQUEST_HEADER) Long userId, @RequestParam String text) {
        return itemService.searchItemsByText(userId, text);
    }
}
