package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

@Component
@Slf4j
public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public ItemDto addItem(Long userId, ItemDto item) {
        return null;
    }

    @Override
    public ItemDto editItem(Long userId, Long itemId, ItemDto item) {
        return null;
    }

    @Override
    public ItemDto findItemById(Long userId, Long itemId) {
        return null;
    }

    @Override
    public List<ItemDto> searchOwnerItems(Long userId) {
        return null;
    }

    @Override
    public List<ItemDto> searchItemsByText(Long userId, String text) {
        return null;
    }
}
