package org.sayem.api.petstore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.commons.lang3.StringUtils;
import org.sayem.api.ContentType;
import org.sayem.api.GetAdapter;
import org.sayem.api.PostAdapter;
import org.sayem.api.PropertiesUtil;
import org.sayem.api.Repository;
import org.sayem.api.RestAdapter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import com.jayway.restassured.path.json.JsonPath;

public class myGetTest {

	
	 private PropertiesUtil factory;

	    @Parameters("properties")
	    public myGetTest(@Optional(Repository.PROPERTIES)
	                        String properties) throws IOException {
	        factory = PropertiesUtil.create(properties);
	    }

	  
	    @Test(groups = "post")
	    public void postRequest() throws  FileNotFoundException {
	      	JsonObject request = PropertiesUtil.jsonFromString(factory.data(Repository.REQUEST));

	        RestAdapter response = GetAdapter.builder()
	                .setContentType(ContentType.JSON)
	                .setRequestObject(request)
	                .setEndPoint(factory.data(Repository.ENDPOINT))
	                .setMethodName(factory.data(Repository.METHOD))
	                .setExpectedStatust(Integer.parseInt(factory.data(Repository.EXPECTED_STATUS)))
	                .build();

	        JsonPath jsonPath = response.execute();
	        jsonPath.prettyPrint();
	
	        
	        /**
		     * @throws FileNotFoundException
		     * 
		     * Data provider should be written here
		     * It should pass all details of a particular Test
		     */
	
	
}
}
