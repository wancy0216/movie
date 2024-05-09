package dev.movies;

import dev.movies.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @version 1.0
 * @Author Wancy
 * @Date 2024/5/7 14:51
 */
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
}
