package Test;

import ResponseModel.AddBookResponse;
import ResponseModel.DeleteBookResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Random;

public class DeleteBookTest extends BaseBookTest{
    @Test
    public void VerifyDeleteBook()
    {
        Random random=new Random();
        String  aisleGenerate = String.format("%04d", random.nextInt(10000));
        String  isbnGenerate = String.format("%04d", random.nextInt(10000));
        Response responseOfAddBook= AddBookResponseMethod("wise and otherwise",aisleGenerate,isbnGenerate,"sudha");
        AddBookResponse addBookResponse=responseOfAddBook.as(AddBookResponse.class);
        System.out.println(addBookResponse.getID());
        Response deletedid=DeleteBookResponseMethod(addBookResponse);
        DeleteBookResponse deleteBookResponse=deletedid.as(DeleteBookResponse.class);
        System.out.println("The book with "+addBookResponse.getID() +"is  deleted with msg"+deleteBookResponse.getMsg());

    }
}
