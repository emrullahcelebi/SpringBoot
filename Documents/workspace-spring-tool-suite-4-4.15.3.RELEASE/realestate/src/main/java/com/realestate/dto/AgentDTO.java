package com.realestate.dto;

import java.util.List;

import com.realestate.domain.AgentImage;
import com.realestate.domain.Property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {

    private Long id;
  
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;
    
    private List<Property> properties;
    
    private AgentImage agentImage;
}
