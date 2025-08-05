package dev.avorakh.tip.redis.om.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.avorakh.tip.redis.om.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends RedisDocumentRepository<Book, String> {}
