package com.visionrent.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageSavedResponse extends VRResponse{
	
	private String imageId;

	
	//Constractor ,message ve success bilgisini VRResponse cont. kullanarak set edecegiz
	public ImageSavedResponse(String imageId, String message, boolean success) {
		super(message,success); //parent class dan yani VRRespons dan geliyor
		this.imageId=imageId;
	}
	
}
