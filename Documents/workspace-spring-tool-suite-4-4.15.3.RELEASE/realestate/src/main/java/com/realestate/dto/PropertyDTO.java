package com.realestate.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.realestate.domain.Agent;
import com.realestate.domain.ImageDB;
import com.realestate.domain.PropertyDetail;
import com.realestate.domain.Review;
import com.realestate.domain.TourRequest;
import com.realestate.domain.enums.PropertyCategory;
import com.realestate.domain.enums.PropertyStatus;
import com.realestate.domain.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

	private Long id;

	private String title;

	private String description;

	private PropertyCategory category;

	private PropertyType type;

	private String bedrooms;

	private String bathrooms;

	private String garages;

	private Double area;

	private Integer price;

	private String location;

	private String address;

	private String country;

	private String city;

	private String district;

	private Date createDate;

	private Long visitCount = 0L;

	private Long likeCount = 0L;

	private PropertyStatus status;

	private Set<ImageDB> image;

	private Agent agent;

	private Set<PropertyDetail> propertyDetails;

	private Set<TourRequest> tourRequests;

	private Set<Review> reviews = new HashSet<>();

}
