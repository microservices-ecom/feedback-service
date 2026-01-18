package com.ecom.feedback.dto;

import java.time.LocalDateTime;

public record FeedbackResponseDto(
        Long id,
        Long userId,
        Long productId,
        String comment,
        Integer rating,
        String status,
        LocalDateTime createdAt
) {}
