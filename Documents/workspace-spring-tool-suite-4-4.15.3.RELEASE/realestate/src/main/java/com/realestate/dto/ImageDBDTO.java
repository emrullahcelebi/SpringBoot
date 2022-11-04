package com.realestate.dto;

import com.realestate.domain.Property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDBDTO {

    private String id;

    private String name;

    private String type;
   
    private byte[] image;

    private Boolean featured;
    
    private Property property;
}
