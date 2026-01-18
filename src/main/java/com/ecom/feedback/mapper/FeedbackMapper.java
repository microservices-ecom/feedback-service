package com.ecom.feedback.mapper;


import com.ecom.feedback.dto.*;
import com.ecom.feedback.model.Feedback;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FeedbackMapper {

    public Feedback toEntity(FeedbackRequestDto dto) {
        return Feedback.builder()
                .userId(dto.userId())
                .productId(dto.productId())
                .comment(dto.comment())
                .rating(dto.rating())
                .status(Feedback.Status.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public FeedbackResponseDto toDto(Feedback fb) {
        return new FeedbackResponseDto(
                fb.getId(),
                fb.getUserId(),
                fb.getProductId(),
                fb.getComment(),
                fb.getRating(),
                fb.getStatus().name(),
                fb.getCreatedAt()
        );
    }
}
