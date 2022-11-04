package com.realestate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ImageDBRequestDTO {
	
    private String name;

    private String type;
   
    private byte[] image;

    private Boolean featured;
 
    private PropertyRequestDTO property;

}
