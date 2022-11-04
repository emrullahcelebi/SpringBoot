package com.realestate.dto.request;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class PropertyRequestDTO {


	@Size(max = 100, message = "Size is exceeded")
    @NotNull(message = "Please enter the homes title")
   
    private String title;

    @NotNull(message = "Please enter the homes description")
    
    private String description;

    
    @NotNull(message = "Please enter the homes description")
    
    private PropertyCategory category;

   
    @NotNull(message = "Please enter the homes type")
   
    private PropertyType type;

    @NotNull(message = "Please enter the homes bedrooms")
   
    private String bedrooms;

    @NotNull(message = "Please enter the homes bathrooms")
    
    private String bathrooms;

    @NotNull(message = "Please enter the homes garages")
    
    private String garages;

    @NotNull(message = "Please enter the homes area")
    
    private Double area;

    @NotNull(message = "Please enter the  homes price")
    
    private Integer price;

    @NotNull(message = "Please enter the homes location")
    
    private String location;

    @NotNull(message = "Please enter the homes address")
   
    private String address;

    @NotNull(message = "Please enter the homes country")
    
    private String country;

    @NotNull(message = "Please enter the homes city")
    
    private String city;

    @NotNull(message = "Please enter the homes district")
    
    private String district;

    @NotNull(message = "Please enter the homes createDate")
    
    private Date createDate;

    @NotNull(message = "Please enter the homes visitCount")
   
    private Long visitCount = 0L;

    @NotNull(message = "Please enter the homes likes")
    
    private Long likeCount = 0L;

    
    @NotNull(message = "Please enter the homes status")
   
    private PropertyStatus status;

    
    private Set<ImageDB> image;

   
    private Agent agent;

    
    private Set<PropertyDetail> propertyDetails;

    private Set<TourRequest> tourRequests;
   
    private Set<Review> reviews;
   
}