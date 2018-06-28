package org.sayem.api.json.placeholder;

import java.io.FileNotFoundException;

import javax.json.JsonObject;

import org.sayem.api.ContentType;
import org.sayem.api.GetAdapter;
import org.sayem.api.PostAdapter;
import org.sayem.api.RestAdapter;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.relevantcodes.extentreports.LogStatus;

public class GETRequest extends BaseTest {
	
	private JsonObject obj;

	public GETRequest(JsonObject jsonValue) {
		// TODO Auto-generated constructor stub
		super(jsonValue.get("testcaseName").toString());
		this.obj = jsonValue;
	}

	@Test
	public void getRequest() throws FileNotFoundException {

		logger.log(LogStatus.INFO,
				"Request: \n" + "URL " + obj.get("endpoint").toString().replaceAll("^\"|\"$", "") + "\n Body : "
										+ obj.get("request") + "\n Expected Status :"
										+ obj.get("expectedStatus").toString().replaceAll("^\"|\"$", ""));
		
		RestAdapter response = GetAdapter.builder().setContentType(ContentType.JSON)
				.setRequestObject(obj.get("request"))
				.setEndPoint(obj.get("endpoint").toString().replaceAll("^\"|\"$", ""))
				.setMethodName(obj.get("method").toString().replaceAll("^\"|\"$", ""))
				.setExpectedStatust(Integer.parseInt(obj.get("expectedStatus").toString().replaceAll("^\"|\"$", "")))
				.build();

		JsonPath jsonPath = response.execute();

		// logger.log(LogStatus.INFO,"Response \n");
		// jsonPath.prettyPrint();

}
}