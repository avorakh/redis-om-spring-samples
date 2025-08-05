package dev.avorakh.tip.redis.om.model;

import com.google.gson.Gson;
import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.serialization.gson.GsonBuidlerFactory;
import java.net.URL;
import java.util.List;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor(staticName = "of")
@Document
public class Book {
    @Id
    private String id;

    @NonNull private String author;

    @NonNull private String description;

    @NonNull private List<String> genres;

    @NonNull private Integer pages;

    @NonNull private String title;

    @NonNull private URL url;

    @NonNull private Integer yearPublished;

    @NonNull private Metrics metrics;

    private List<InventoryItem> inventory;

    @Override
    public String toString() {
        return gson.toJson(this);
    }

    // used to print the object as a JSON document
    private static final Gson gson =
            GsonBuidlerFactory.getBuilder().setPrettyPrinting().create();
}
