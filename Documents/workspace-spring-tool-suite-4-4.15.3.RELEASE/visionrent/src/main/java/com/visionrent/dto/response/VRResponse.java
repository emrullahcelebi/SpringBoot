package com.visionrent.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VRResponse {
	//kullaniciya mesaj vermek icin yapiyoruz
	private String message; //yapilan islemin sonucunda mesaj veriyoruz 
	boolean success; //true demek icin

}
