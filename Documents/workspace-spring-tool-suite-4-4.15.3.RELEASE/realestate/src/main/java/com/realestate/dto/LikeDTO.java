package com.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {

	 private Long id;

	    private Boolean isLiked;

	    private Long userId;

	    private Long propertyId;
}
