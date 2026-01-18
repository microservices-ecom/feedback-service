package com.ecom.feedback.dto;

public record RatingSummaryDto(
        Double avgRating,
        Long totalCount,
        Long oneStar,
        Long twoStar,
        Long threeStar,
        Long fourStar,
        Long fiveStar
) {}
