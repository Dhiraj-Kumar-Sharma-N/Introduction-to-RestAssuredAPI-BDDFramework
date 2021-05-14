package StepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")

	public void BeforeSecenario() throws IOException {

		// Validate if Place Id exist only then execute the Scenario

		StepDefinition StpDefObj = new StepDefinition();
		if (StepDefinition.PlaceID == null) {
			// if only deleteplace tag is executed this method checks if the Place ID is
			// empty and create Addplace Request
			StpDefObj.add_place_payload_with_and("Dhiraj", "Kannada", "Mandya");
			StpDefObj.user_calls_with_http_request("AddPlaceAPI", "POST");
			StpDefObj.verify_place_id_created_maps_to_using("Dhiraj", "GetPlaceAPI");

		}
	}
}