package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDto addItem(Long userId, ItemDto item) {
        return itemRepository.addItem(userId, item);
    }

    public ItemDto editItem(Long userId, Long itemId, ItemDto item) {
        return itemRepository.editItem(userId, itemId, item);
    }

    public ItemDto findItemById(Long userId, Long itemId) {
        return itemRepository.findItemById(userId, itemId);
    }

    public List<ItemDto> searchOwnerItems(Long userId) {
        return itemRepository.searchOwnerItems(userId);
    }

    public List<ItemDto> searchItemsByText(Long userId, String text) {
        return itemRepository.searchItemsByText(userId, text);
    }
}
