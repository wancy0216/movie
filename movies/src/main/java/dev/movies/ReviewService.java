package dev.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author Wancy
 * @Date 2024/5/7 14:52
 */
@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId){
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class) // 更新 Movie 类型的文档
                .matching(Criteria.where("imdbId").is(imdbId)) // 匹配 imdbId 为指定值的文档
                .apply(new Update().push("reviewIds").value(reviewBody)) // 将新评论添加到评论列表中
                .first(); // 只更新匹配到的第一个文档

        return review;
    }

}
