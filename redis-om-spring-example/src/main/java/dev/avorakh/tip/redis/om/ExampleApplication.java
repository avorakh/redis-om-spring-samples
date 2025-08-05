package dev.avorakh.tip.redis.om;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import dev.avorakh.tip.redis.om.model.Book;
import dev.avorakh.tip.redis.om.model.Book$;
import dev.avorakh.tip.redis.om.model.InventoryItem;
import dev.avorakh.tip.redis.om.model.Metrics;
import dev.avorakh.tip.redis.om.repository.BookRepository;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "dev.avorakh.tip.redis.om")
public class ExampleApplication {

    @Bean
    CommandLineRunner loadTestData(BookRepository repository) {
        return args -> {
            // Delete all books from the database
            repository.deleteAll();

            // Create an instance of the Book model.
            Book newBook = Book.of(
                    "Redis Staff",
                    "This is a book all about Redis.",
                    List.of("redis", "tech", "computers"),
                    1000,
                    "Redis for Beginners",
                    new URL("https://university.redis.com/courses/ru204/"),
                    2022,
                    Metrics.of(4000, 4.5));
            // set inventory items
            newBook.setInventory(List.of(
                    InventoryItem.of("on_loan", "999_1"), //
                    InventoryItem.of("maintenance", "999_2") //
                    ));

            // Save the book to Redis.
            repository.save(newBook);
            log.info("Saved book in Redis.");

            // Get the locally generated ULID for this book.
            log.info(String.format("new_book ULID: %s", newBook.getId()));

            // Retrieve the book from Redis.
            Optional<Book> maybeABook = repository.findById(newBook.getId());
            Book aBook = maybeABook.get();
            log.info("Retrieved from Redis:");
            log.info(aBook.toString());

            // Update the author field and save it.
            repository.updateField(aBook, Book$.AUTHOR, "Redis Book");

            // Retrive book after update
            Optional<Book> maybeTheSameBook = repository.findById(newBook.getId());
            Book theSameBook = maybeTheSameBook.get();
            log.info("Retrieved from Redis after update:");
            log.info(theSameBook.toString());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
