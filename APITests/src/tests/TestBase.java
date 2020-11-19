package tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestBase {
	private List<String> secrets = new ArrayList<String>();
	
	public TestBase() throws IOException {
		FileReader reader = null;
		BufferedReader inputFile = null;
		
		try {
			reader = new FileReader("resources/secrets");
			inputFile = new BufferedReader(reader);
			
			String line = null;
			while ((line = inputFile.readLine())!= null) {
				secrets.add(line);
			}
		}
		finally
		{
			inputFile.close();
			reader.close();
		}
	}
	
	public String post(String url, String body) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL fullUrl = new URL(String.format("%s/%s", getBaseUrl(), url));
			
			connection = (HttpURLConnection) fullUrl.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			setProperties(connection);
			
			sendRequestBody(connection, body);
			
			return getResponse(connection);
		}
		finally {
			connection.disconnect();
		}	
	}
	
	public String get(String url) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL fullUrl = new URL(String.format("%s/%s", getBaseUrl(), url));
			
			connection = (HttpURLConnection) fullUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			
			setProperties(connection);
			
			return getResponse(connection);
		}
		finally {
			connection.disconnect();
		}
	}
	
	public String delete(String url) throws Exception {
		HttpURLConnection connection = null;
		try {
			URL fullUrl = new URL(String.format("%s/%s", getBaseUrl(), url));
			
			connection = (HttpURLConnection) fullUrl.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setDoInput(true);
			
			setProperties(connection);
			
			return getResponse(connection);
		}
		finally {
			connection.disconnect();
		}
	}
	
	private void sendRequestBody(HttpURLConnection connection, String body) throws Exception {
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(body);
		writer.flush();
	}
	
	private String getResponse(HttpURLConnection connection) throws Exception {
		StringBuilder sbuilder = new StringBuilder();
		int HttpResult = connection.getResponseCode(); 
		
		if (HttpResult == HttpURLConnection.HTTP_OK) {
		    BufferedReader br = new BufferedReader(
		            new InputStreamReader(connection.getInputStream(), "utf-8"));
		    String line = null;  
		    while ((line = br.readLine()) != null) {  
		    	sbuilder.append(line + "\n");  
		    }
		    br.close();
		    return sbuilder.toString();
		}
		throw new Exception(String.format("HTTP Code: %", HttpResult));
	}
	
	private void setProperties(HttpURLConnection connection) {
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Authorization", String.format("Bearer %s", getToken()));
	}
		
	private String getBaseUrl() {
		return secrets.get(0);
	}
	
	private String getToken() {
		return secrets.get(1);
	}
}
