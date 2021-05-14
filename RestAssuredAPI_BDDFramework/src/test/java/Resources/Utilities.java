package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

public class Utilities {
	public static RequestSpecification ReqSpecObj;

	public RequestSpecification prefixRequestBuilder() throws IOException {

		if (ReqSpecObj == null) {
			PrintStream PSObj = new PrintStream(new FileOutputStream("logs.txt"));
			ReqSpecObj = new io.restassured.builder.RequestSpecBuilder().addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).setBaseUri(RetrieveBaseURL("BaseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(PSObj))
					.addFilter(ResponseLoggingFilter.logResponseTo(PSObj)).build();

			return ReqSpecObj;
		}
		return ReqSpecObj;
	}

	public String RetrieveBaseURL(String Key) throws IOException {

		Properties PropObj = new Properties();
		FileInputStream FisObj = new FileInputStream("./src/test/java/Resources/Config.properties");
		PropObj.load(FisObj);
		String URL = PropObj.getProperty(Key);
		return URL;
	}

	public String GetJsonPathValue(Response JsonResponse, String JSONPATH) {
		JsonPath jsobj = new JsonPath(JsonResponse.asString());
		return jsobj.get(JSONPATH).toString();

	}
}
