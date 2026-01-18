package com.ecom.feedback.service.impl;


import com.ecom.feedback.dto.*;
import com.ecom.feedback.exception.ResourceExistsException;
import com.ecom.feedback.exception.ResourceNotFoundException;
import com.ecom.feedback.mapper.FeedbackMapper;
import com.ecom.feedback.model.Feedback;
import com.ecom.feedback.repository.FeedbackRepository;
import com.ecom.feedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

	private final FeedbackRepository repository;
	private final FeedbackMapper mapper;

	@Override
	public FeedbackResponseDto addFeedback(FeedbackRequestDto dto) {
		if (repository.existsByUserIdAndProductId(dto.userId(), dto.productId())) {
			throw new ResourceExistsException("Feedback already exists from this user");
		}

		Feedback saved = repository.save(mapper.toEntity(dto));
		return mapper.toDto(saved);
	}

	@Override
	public FeedbackResponseDto getFeedback(Long id) {
		Feedback fb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
		return mapper.toDto(fb);
	}

	@Override
	public RatingSummaryDto getRatingSummary(Long productId) {
		List<Object[]> rows = repository.aggregateRatings(productId);

		long[] stars = new long[6];
		long total = 0;

		for (Object[] r : rows) {
			int rating = (int) r[0];
			long count = (long) r[1];
			stars[rating] = count;
			total += count;
		}

		double avg = total == 0 ? 0
				: (stars[1] + stars[2] * 2 + stars[3] * 3 + stars[4] * 4 + stars[5] * 5) / (double) total;

		return new RatingSummaryDto(avg, total, stars[1], stars[2], stars[3], stars[4], stars[5]);
	}
}
