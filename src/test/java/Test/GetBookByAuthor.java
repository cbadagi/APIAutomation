package Test;

import RequestModel.DeleteBookRequest;
import ResponseModel.AddBookResponse;
import ResponseModel.DeleteBookResponse;
import ResponseModel.GetBookResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class GetBookByAuthor extends BaseBookTest {

    @DataProvider(name = "Booklist")
    public static Object[][] Books() {
        return new Object[][]
                {
                        {"book1", "45639", "634255", "boxom"},
                        {"book2", "45633", "3434", "boxom"},
                        {"book3", "45633", "65543565", "boxom"}
                };
    }

    @Test(dataProvider = "Booklist",priority=1)
    public void VerifyGetBookByAuthorMethod(String bookName, String isbn, String ailse, String Author) {
        System.out.println("VerifyBookBy Author");
        Response addBookResponseMethod = AddBookResponseMethod(bookName, isbn, ailse, Author);
        AddBookResponse addBookResponse = addBookResponseMethod.body().as(AddBookResponse.class);
        System.out.println(addBookResponse.getID());
        Response getBookResponse = given().queryParam("ID", addBookResponse.getID()).
                when().get("/Library/GetBook.php").then().statusCode(200).extract().response();
        GetBookResponse[] book = getBookResponse.as(GetBookResponse[].class);
        Assert.assertEquals(Books().length,3);
        Assert.assertEquals(book[0].getAuthor(),"boxom","The Author name is mismatch");
            System.out.println("The book Author Name is "+book[0].getAuthor());
            System.out.println("The Book name is "+ book[0].getBookName());
        }
    @DataProvider(name = "Booklist2")
    public static Object[][] Book2() {
        return new Object[][]
                {
                        {"bookl1", "7575", "6565", "boxom"},
                        {"bookl2", "7578", "111", "boxom"},
                        {"bookl3", "45633", "11111", "boxom"}
                };
    }
@Test(dataProvider = "Booklist2")
        public void VerifyGetBookByAuthorAndDelete(String bookName, String isbn, String ailse, String Author)
        {
            DeleteBookRequest[] deleteBookRequests=new DeleteBookRequest[]
                    {
                            new DeleteBookRequest()
                    };
            DeleteBookRequest deleteBookRequest=deleteBookRequests[0];
            System.out.println("verify the book by author and delete");
            Response addBookResponseMethod = AddBookResponseMethod(bookName, isbn, ailse, Author);
            AddBookResponse addBookResponse = addBookResponseMethod.body().as(AddBookResponse.class);
            //System.out.println(addBookResponse.getID());
            Response getBookResponse = given().queryParam(addBookResponse.getID()).
                    when().get("/Library/GetBook.php").then().statusCode(200).extract().response();
            GetBookResponse[] book = getBookResponse.as(GetBookResponse[].class);
            //book[0].getAuthor();
            
            deleteBookRequest.setID((book[0].getIsbn()+book[0].getAisle()));
            Response deleteBookResponse = given()
                    .body(deleteBookRequest)
                    .post("/Library/DeleteBook.php")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();
            DeleteBookResponse deletedid=deleteBookResponse.as(DeleteBookResponse.class);
            Assert.assertEquals(deletedid.getMsg(),"book is successfully deleted");
            System.out.println("The book with "+addBookResponse.getID() +"is  deleted with msg"+deletedid.getMsg());
            Assert.assertEquals(Books().length,2);
            System.out.println("The Book name is "+ book[0].getBookName());




        }
    }

