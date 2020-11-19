package tests;

import static org.junit.Assert.*;

import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

public class ShoppingListTests {

	private TestBase base;
	private String listId;
	private final String endpoint = "list/v2";
	
	@Before
	public void setUp() throws Exception {
		base = new TestBase();
		
		String name = UUID.randomUUID().toString();
		JSONObject body = new JSONObject();
		
		body.put("name", name);
		body.put("primary", false);
		
		String response = base.post(endpoint, body.toJSONString());
		JSONObject responseJson = (JSONObject) new JSONParser().parse(response);
		listId = (String) responseJson.get("id");
	}
	
	@Test
	public void getsList() throws Exception {
		String response = base.get(String.format("%s/%s", endpoint, listId));
		JSONObject responseJson = (JSONObject) new JSONParser().parse(response);
		
		assertEquals(listId, (String) responseJson.get("id"));
		assertEquals(listId, (String) responseJson.get("content"));
	}
	
	@Test
	public void deletesList() throws Exception  {
		String response = base.delete(String.format("%s/%s", endpoint, listId));
				
		assertEquals("shoppingList.notFound", response);
	}
}
