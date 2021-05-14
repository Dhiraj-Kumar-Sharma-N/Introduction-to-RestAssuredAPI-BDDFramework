package StepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import Resources.Enum_APIResources;
import Resources.InputTestDataBuilder;
import Resources.Utilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Utilities {
	RequestSpecification RequestPayload;
	Response ResponsePayload;
	static String PlaceID;

	@Given("Add Place Payload with {string} {string} and {string}")
	public void add_place_payload_with_and(String Name, String Language, String Address) throws IOException {

		RequestPayload = given().spec(prefixRequestBuilder())
				.body(InputTestDataBuilder.PostInputPayload(Name, Language, Address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String APIName, String HTTPMethod) {
		String URIResource = Enum_APIResources.valueOf(APIName).GetResourceURI();
		if (HTTPMethod.equalsIgnoreCase("POST")) 
			ResponsePayload = RequestPayload.when().post(URIResource);
		else if (HTTPMethod.equalsIgnoreCase("DELETE"))
			ResponsePayload = RequestPayload.when().delete(URIResource);
		else if (HTTPMethod.equalsIgnoreCase("PUT"))
			ResponsePayload = RequestPayload.when().put(URIResource);
		else if (HTTPMethod.equalsIgnoreCase("GET"))
			ResponsePayload = RequestPayload.when().get(URIResource);
		else
			System.out.println("Invalid HTTP Request");

		ResponseSpecification ResSpecObj = new io.restassured.builder.ResponseSpecBuilder()
				.build();
		ResponsePayload = ResponsePayload.then().spec(ResSpecObj).extract().response();

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(ResponsePayload.getStatusCode(), (int) int1);
		System.out.println(ResponsePayload.asString());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String ExpectedKey, String ExpectedValue) {
		assertEquals(GetJsonPathValue(ResponsePayload, ExpectedKey), ExpectedValue);

	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String AddedName, String APIUsed) throws IOException {
		PlaceID = GetJsonPathValue(ResponsePayload, "place_id");
		RequestPayload = given().spec(prefixRequestBuilder()).queryParam("place_id", PlaceID);
		user_calls_with_http_request(APIUsed, "GET");
		assertEquals(GetJsonPathValue(ResponsePayload, "name"), AddedName);
		System.out.println("PASSED");
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	RequestPayload = given().spec(prefixRequestBuilder()).body(InputTestDataBuilder.DeletePayload(PlaceID));
				
	}

}