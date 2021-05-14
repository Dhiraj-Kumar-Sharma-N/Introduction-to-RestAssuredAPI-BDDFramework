package Resources;

public enum Enum_APIResources {

	
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	UpdatePlaceAPI("maps/api/place/update/json"),
	DeletePlaceAPI("maps/api/place/delete/json");
	private String ResourceURI;
	
	Enum_APIResources(String resourceuri) {
		this.ResourceURI=resourceuri;
		
	}
	
	public String GetResourceURI() {
		return ResourceURI;
	}
}
