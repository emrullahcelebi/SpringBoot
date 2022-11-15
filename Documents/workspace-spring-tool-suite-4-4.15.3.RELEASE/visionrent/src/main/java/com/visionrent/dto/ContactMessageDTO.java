package com.visionrent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessageDTO {
	//bu server den gelecegi icin kontrollere gerek yok
	private Long id;  
	

	private String name;
	

	private String subject;
	

	private String body;
	

	private String email;

}
