package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * TODO Sprint add-controllers.
 */

@Data
@Builder
public class Item {
    private Long id;
    private final Long ownerId;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @Builder.Default
    private Boolean available = true;
}
