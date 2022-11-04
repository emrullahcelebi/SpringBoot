package com.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgentImageDTO {

	  private String id;
	  
	    private String name;
	    
	    private String type;
	    
	    private byte[] data;
}
