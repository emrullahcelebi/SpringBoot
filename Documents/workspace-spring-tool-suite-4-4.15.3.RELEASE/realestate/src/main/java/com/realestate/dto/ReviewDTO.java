package com.realestate.dto;

import java.time.LocalDate;

import com.realestate.domain.Property;
import com.realestate.domain.User;
import com.realestate.domain.enums.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

	private Long id;

	private String review;

	private LocalDate activationDate;

	private Integer score;

	private ReviewStatus status;

	private User user;

	private Property property;
}
