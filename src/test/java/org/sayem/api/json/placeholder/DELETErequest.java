package org.sayem.api.json.placeholder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.io.IoBuilder;
import org.sayem.api.ContentType;
import org.sayem.api.DeleteAdapter;
import org.sayem.api.GetAdapter;
import org.sayem.api.PostAdapter;
import org.sayem.api.PropertiesUtil;
import org.sayem.api.Repository;
import org.sayem.api.RestAdapter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.path.json.JsonPath;
import com.relevantcodes.extentreports.LogStatus;

public class DELETErequest extends BaseTest {

	private JsonObject obj;

	public DELETErequest(JsonObject jsonValue) {
		// TODO Auto-generated constructor stub
		super(jsonValue.get("testcaseName").toString());
		this.obj = jsonValue;
	}

	@Test
	public void postRequest() throws FileNotFoundException {

		logger.log(LogStatus.INFO,
				"Request: \n" + "URL " + obj.get("endpoint").toString().replaceAll("^\"|\"$", "") + "\n Body : "
										+ obj.get("request") + "\n Expected Status :"
										+ obj.get("expectedStatus").toString().replaceAll("^\"|\"$", ""));
		
		RestAdapter response = DeleteAdapter.builder().setContentType(ContentType.JSON)
				.setRequestObject(obj.get("request"))
				.setEndPoint(obj.get("endpoint").toString().replaceAll("^\"|\"$", ""))
				.setMethodName(obj.get("method").toString().replaceAll("^\"|\"$", ""))
				.setExpectedStatust(Integer.parseInt(obj.get("expectedStatus").toString().replaceAll("^\"|\"$", "")))
				.build();

		JsonPath jsonPath = response.execute();

		 logger.log(LogStatus.INFO,"Response \n");
		 jsonPath.prettyPrint();
	}
}
