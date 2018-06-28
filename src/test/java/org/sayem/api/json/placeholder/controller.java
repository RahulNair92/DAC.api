package org.sayem.api.json.placeholder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.testng.ITestContext;
import org.testng.annotations.Factory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.mongodb.diagnostics.logging.Logger;

public class controller {

	@Factory
	public Object[] createinstances(final ITestContext testContext) {

		File jsonInputFile = new File(getFilePath(testContext.getName()));
		InputStream is;

		try {
			is = new FileInputStream(jsonInputFile);
			// Create JsonReader from Json.
			JsonReader reader = Json.createReader(is);
			// Get the JsonObject structure from JsonReader.
			JsonObject empObj = reader.readObject();
			reader.close();
			int i = 0;
			Object[] testcase = new Object[empObj.size()];

			switch (testContext.getName().trim()) {

			case "POST request":
				for (String Key : empObj.keySet()) {
					System.out.println(Key);
					Object value = empObj.get(Key);
					testcase[i] = new PostRequest(empObj.getJsonObject(Key));
					i = i + 1;
				}
				break;
			case "PUT request":
				for (String Key : empObj.keySet()) {
					System.out.println(Key);
					Object value = empObj.get(Key);
					testcase[i] = new PutRequest(empObj.getJsonObject(Key));
					i = i + 1;
				}
				break;
			case "DELETE request":
				for (String Key : empObj.keySet()) {
					System.out.println(Key);
					Object value = empObj.get(Key);
					testcase[i] = new DELETErequest(empObj.getJsonObject(Key));
					i = i + 1;
				}
				break;
			default:
				for (String Key : empObj.keySet()) {
					System.out.println(Key);
					Object value = empObj.get(Key);
					testcase[i] = new GETRequest(empObj.getJsonObject(Key));
					i = i + 1;
				}
			}

			return testcase;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public String getFilePath(String action) {

		String folder = "./testcases/";
		switch (action) {
		case "POST request":
			return folder + "POST.json";
		case "PUT request":
			return folder + "PUT.json";
		case "DELETE request":
			return folder + "DELETE.json";
		default:
			return folder + "GET.json";

		}
	}

}
