package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.IdNotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class ItemRepositoryImpl implements ItemRepository {
    private final UserRepository userRepository;
    private final List<Item> itemList;
    private final ItemMapper itemMapper;
    private Long id = 1L;

    @Autowired
    public ItemRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.itemList = new ArrayList<>();
        this.itemMapper = new ItemMapper();
    }

    @Override
    public ItemDto addItem(Long userId, ItemDto item) {
        userRepository.findUserById(userId);

        if (checkFields(item.getName()) || checkFields(item.getDescription()) || item.getAvailable() == null) {
            throw new ValidationException("Название, описание и статус вещи должны быть заполнены.");
        }

        item.setId(id);
        itemList.add(itemMapper.convertToItem(userId, item));

        log.info("Вещь добавлена: " + item);

        id++;
        return item;
    }

    @Override
    public ItemDto editItem(Long userId, Long itemId, ItemDto item) {
        userRepository.findUserById(userId);

        for (Item i : itemList) {
            if (Objects.equals(i.getId(), itemId)) {
                if (Objects.equals(i.getOwnerId(), userId)) {
                    if (!checkFields(item.getName())) {
                        i.setName(item.getName());
                    }
                    if (!checkFields(item.getDescription())) {
                        i.setDescription(item.getDescription());
                    }
                    if (item.getAvailable() != null) {
                        i.setAvailable(item.getAvailable());
                    }
                    return itemMapper.convertToItemDto(i);
                }
                throw new IdNotFoundException("Пользователь не является владельцем вещи.");
            }
        }

        throw new IdNotFoundException("Указан неверный id вещи");
    }


    @Override
    public ItemDto findItemById(Long userId, Long itemId) {
        userRepository.findUserById(userId);

        for (Item i : itemList) {
            if (Objects.equals(i.getId(), itemId)) {
                return itemMapper.convertToItemDto(i);
            }
        }

        throw new IdNotFoundException("Указан неверный id вещи");
    }

    @Override
    public List<ItemDto> searchOwnerItems(Long userId) {
        userRepository.findUserById(userId);
        List<ItemDto> ownerItemsList = new ArrayList<>();

        for (Item i : itemList) {
            if (Objects.equals(i.getOwnerId(), userId)) {
                ownerItemsList.add(itemMapper.convertToItemDto(i));
            }
        }

        return ownerItemsList;
    }

    @Override
    public List<ItemDto> searchItemsByText(Long userId, String text) {
        userRepository.findUserById(userId);
        List<ItemDto> itemsByTextList = new ArrayList<>();

        for (Item i : itemList) {
            if ((i.getName().toLowerCase().contains(text.toLowerCase())
                    || i.getDescription().toLowerCase().contains(text.toLowerCase()))
                    && i.getAvailable() && !text.isEmpty()) {
                itemsByTextList.add(itemMapper.convertToItemDto(i));
            }
        }

        return itemsByTextList;
    }

    private boolean checkFields(String field) {
        return field == null || field.isBlank();
    }
}
