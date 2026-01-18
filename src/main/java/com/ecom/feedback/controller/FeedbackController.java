package com.ecom.feedback.controller;


import com.ecom.feedback.dto.*;
import com.ecom.feedback.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

	private final FeedbackService service;

	@PostMapping
	public FeedbackResponseDto add(@Valid @RequestBody FeedbackRequestDto dto) {
		return service.addFeedback(dto);
	}

	@GetMapping("/{id}")
	public FeedbackResponseDto get(@PathVariable Long id) {
		return service.getFeedback(id);
	}

	@GetMapping("/product/{productId}/summary")
	public RatingSummaryDto summary(@PathVariable Long productId) {
		return service.getRatingSummary(productId);
	}
}
