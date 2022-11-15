

package com.visionrent.domain.enums;

public enum RoleType {
	
	ROLE_CUSTOMER("Customer"),
	ROLE_ADMIN("Administrator");
	
	private String name;
	
	// constructorÄ± dÄ±ÅŸarÄ± aÃ§mamak iÃ§in private yapÄ±yoruz
	private RoleType(String name) {
		this.name = name ;
	}
	
	public String getName() {
		return name ;
	}
	
	

}





















//package com.visionrent.domain.enums;
//
//public enum RoleType {
//	
//	ROLE_CUSTOMER("Customer"),//on tarafa parantez ici gidecek
//	ROLE_ADMIN("Administrator");
//	
//	private String name;
//	
//	// constructorı dışarı açmamak için private yapıyoruz
//	private RoleType(String name) {
//		this.name = name ;
//	}
//	
//	//her ROLE_ADMIN ismi admin olacak gibi 
//	public String getName() {
//		return name ;
//	}
//	
//	
//}