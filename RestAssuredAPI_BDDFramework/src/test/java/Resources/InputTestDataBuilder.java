package Resources;

import java.util.ArrayList;
import java.util.List;

import POJO_Classes.AddPlaceRequest_POJO;
import POJO_Classes.NestedLocatonRequest_POJO;

public class InputTestDataBuilder {

	public static AddPlaceRequest_POJO PostInputPayload(String NAME, String LANGUAGE, String ADDRESS) {

		// SET VALUES USING THE POJO CLASS IN ORDER TO CONVERT JAVA OBJECT TO JSON

		POJO_Classes.AddPlaceRequest_POJO Obj = new POJO_Classes.AddPlaceRequest_POJO();
		Obj.setAccuracy(90);
		Obj.setAddress(ADDRESS);
		Obj.setLanguage(LANGUAGE);
		Obj.setName(NAME);
		Obj.setPhone_number("(+91) 9432198754");
		Obj.setWebsite("https://Testing123api.com/");

		List<String> ListofTypes = new ArrayList<String>();
		ListofTypes.add("Apple");
		ListofTypes.add("Orange");

		Obj.setTypes(ListofTypes);

		NestedLocatonRequest_POJO obj1 = new NestedLocatonRequest_POJO();
		obj1.setLat(22.1234);
		obj1.setLng(44.9876);

		Obj.setLocation(obj1);

		return Obj;
	}

	public static String DeletePayload(String PLACEID) {

		String str = "{\r\n" + "    \"place_id\":\"" + PLACEID + "\"   	 	\r\n" + "}\r\n" + "";

		return str;

	}
}
