package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemRepository {

    ItemDto addItem(Long userId, ItemDto itemDto);

    ItemDto editItem(Long userId, Long itemId, ItemDto itemDto);

    ItemDto findItemById(Long userId, Long itemId);

    List<ItemDto> searchOwnerItems(Long userId);

    List<ItemDto> searchItemsByText(Long userId, String text);
}
