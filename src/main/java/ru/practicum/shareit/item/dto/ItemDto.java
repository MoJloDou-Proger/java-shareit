package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * TODO Sprint add-controllers.
 */

@Data
@Builder
public class ItemDto {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @Builder.Default
    private boolean available = true;
}
