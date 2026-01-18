package com.ecom.feedback.dto;

import jakarta.validation.constraints.*;

public record FeedbackRequestDto(
        @NotNull Long userId,
        @NotNull Long productId,
        @NotBlank String comment,
        @Min(1) @Max(5) Integer rating
) {}
