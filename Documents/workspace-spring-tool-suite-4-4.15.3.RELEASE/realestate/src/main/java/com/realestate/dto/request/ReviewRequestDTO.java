package com.realestate.dto.request;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import com.realestate.domain.enums.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReviewRequestDTO { 
    
    private String review;

    @NotNull(message = "Please enter the pick up time of the reservation")
   
    private LocalDate activationDate;
   
    private Integer score;
   
    private PropertyRequestDTO property;
    
    private UserRequestDTO user;
    
    private ReviewStatus status;

 
}