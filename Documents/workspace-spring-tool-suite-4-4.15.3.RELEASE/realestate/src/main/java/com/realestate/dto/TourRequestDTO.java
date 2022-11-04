package com.realestate.dto;

import java.time.LocalDateTime;

import com.realestate.domain.Property;
import com.realestate.domain.User;
import com.realestate.domain.enums.TourRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourRequestDTO {

	private Long id;

	private LocalDateTime tourRequestTime;

	private String adult;

	private String child;

	private TourRequestStatus status;

	private Property property;

	private User user;
}
