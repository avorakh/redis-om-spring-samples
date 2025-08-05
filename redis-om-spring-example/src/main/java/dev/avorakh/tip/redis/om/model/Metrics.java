package dev.avorakh.tip.redis.om.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

// This class models the embedded "metrics" object.
@Data
@RequiredArgsConstructor(staticName = "of")
public class Metrics {
    @NonNull private Integer ratingVotes;

    @NonNull private Double score;
}
