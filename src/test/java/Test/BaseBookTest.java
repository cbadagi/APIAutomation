package Test;

import RequestModel.AddBookRequest;

import RequestModel.DeleteBookRequest;
import ResponseModel.AddBookResponse;
import ResponseModel.DeleteBookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;

import java.util.Random;

import static io.restassured.RestAssured.*;

public class BaseBookTest {


    public Response AddBookResponseMethod(String bookName, String isbn, String aisle, String author)
    {
        RestAssured.baseURI="http://216.10.245.166";
        AddBookRequest addBookRequest=new AddBookRequest();
        addBookRequest.setBookName(bookName);
        addBookRequest.setIsbn(isbn);
        addBookRequest.setAisle(aisle);
        addBookRequest.setAuthorName(author);
        Response addbookresponse=given().header("Content-Type","application/json").body(addBookRequest)
                .when().post("/Library/Addbook.php").then().
                extract().response();
        System.out.println(addbookresponse.asString());
        return addbookresponse;
    }
    public Response GetBookResponseID(String ID )
    {
        Response getBookResponse=given().queryParam("ID",ID).when().get("Library/GetBook.php").then().statusCode(200).extract().response();
        return getBookResponse;
    }

    public Response DeleteBookResponseMethod(AddBookResponse response )
    {
        JSONObject deleteParameter=new JSONObject() ;
        deleteParameter.put("ID",response.getID());
         Response deleteResponse=given().header("Content-Type","application/json").body(deleteParameter.toJSONString()).when().post("/Library/DeleteBook.php").then().statusCode(200).extract().response();
        return deleteResponse;
    }

}
