package com.ecom.feedback.service;

import com.ecom.feedback.dto.*;

public interface FeedbackService {

    FeedbackResponseDto addFeedback(FeedbackRequestDto dto);

    FeedbackResponseDto getFeedback(Long id);

    RatingSummaryDto getRatingSummary(Long productId);
}
