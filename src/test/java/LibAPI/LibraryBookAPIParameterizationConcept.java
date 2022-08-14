package LibAPI;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Payloads.AllPayloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LibraryBookAPIParameterizationConcept {

	//public String isbnval = RandomStringUtils.randomAlphabetic(1);
	//public String aisle = RandomStringUtils.randomNumeric(3);
	public static String CreatedBookID;


	@Test(dataProvider = "getData")
	public void AaddBook(String isbnval, int aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";

		Response response = RestAssured.given().log().all().header("Content-Type","application/json")
				.body(AllPayloads.addBookPayload(isbnval, aisle)).when().post("/Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().response();


		//verifying status code

		int status = response.statusCode();
		Assert.assertEquals(status, 200);

		//verifying content type

		String statusline = response.statusLine();
		System.out.println(statusline.contains("HTTP/1.1 200 OK"));

		Long timetaken = response.getTime();
		System.out.println(timetaken);

		JsonPath js = Payloads.AllPayloads.rawtoJSON(response);

		String message = js.getString("Msg");
		Assert.assertEquals(message, "successfully added");

		String bookid = js.getString("ID");
		System.out.println(bookid);

		String i = isbnval.toLowerCase();
		String b = "BCD"+i+aisle;
		String BookIdInBody  = b.toLowerCase();
		String CreatedBookID = bookid.toLowerCase();

		Assert.assertEquals(BookIdInBody, CreatedBookID);


		System.out.println(CreatedBookID.contains(BookIdInBody));

		Assert.assertEquals(response.header("Server"), "Apache");
		Assert.assertEquals(response.header("Content-Type"), "application/json;charset=UTF-8");


		Response response11 = RestAssured.given().queryParam("AuthorName", "John foe")
				.when().get("/Library/GetBook.php").then().log().all().assertThat().statusCode(200)
				.statusLine("HTTP/1.1 200 OK").extract().response();

		System.out.println(response11.body().asString());

		String response12  = RestAssured.given().log().all().queryParam("ID", CreatedBookID)
				.when().get("Library/GetBook.php").then().log().all()
				.assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK")
				.extract().response().asString();

		System.out.println(response12);

		Response responsedel = RestAssured.given().log().all().body(AllPayloads.deletePayLoad(CreatedBookID))
				.when().delete("/Library/DeleteBook.php").then().log().all().extract().response();

		int responseCode1 = responsedel.getStatusCode();
		System.out.println(responseCode1);

		JsonPath js1 = AllPayloads.rawtoJSON(responsedel);

		String deletedSucessMesg = js1.getString("msg");
		System.out.println(deletedSucessMesg);



	}


	@DataProvider
	public Object[][] getData()
	{
		Object obj[][] = new Object[3][2];

		obj[0][0] = "john";
		obj[0][1] = 1234;

		obj[1][0] = "Rahul";
		obj[1][1] = 56789;

		obj[2][0] = "Bheem";
		obj[2][1] = 54321;

		return obj;
	}



}
