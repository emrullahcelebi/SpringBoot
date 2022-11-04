package com.realestate.dto.request;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import com.realestate.domain.enums.TourRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourRequestRequestDTO {

    
    @NotNull(message = "Please enter the pick up time of the tour")
    
    private LocalDateTime tourRequestTime;

    @NotNull(message = "please enter adult")
   
    private String adult;

    @NotNull(message = "please enter adult")
    
    private  String child;
 
    private UserRequestDTO user;
  
    private PropertyRequestDTO property;
  
    @NotNull(message = "Please enter the TourRequest status")
    private TourRequestStatus status;

}