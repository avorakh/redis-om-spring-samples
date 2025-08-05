package dev.avorakh.tip.redis.om.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

// This class models the "inventory" array of objects.
@Data
@RequiredArgsConstructor(staticName = "of")
public class InventoryItem {
    @NonNull private String status;

    @NonNull private String stockId;
}
