package com.ecom.feedback.repository;

import com.ecom.feedback.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    boolean existsByUserIdAndProductId(Long userId, Long productId);

    List<Feedback> findByProductIdAndDeletedFalse(Long productId);

    @Query("""
            SELECT f.rating, COUNT(f)
            FROM Feedback f
            WHERE f.productId = :productId AND f.deleted = false
            GROUP BY f.rating
            """)
    List<Object[]> aggregateRatings(Long productId);
}
