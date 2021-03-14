package Test;

import ResponseModel.AddBookResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Random;
public class AddBookTest extends BaseBookTest{


    @Test
    public void VerifyAddBookAndValidateWithId()
    {
        Random random=new Random();
       String  aisleGenerate = String.format("%04d", random.nextInt(10000));
       String  isbnGenerate = String.format("%04d", random.nextInt(10000));
        String ExpectedResult=aisleGenerate+isbnGenerate;
        //get all the addbook response in response variable;
        Response response = AddBookResponseMethod("wise and otherwise",aisleGenerate,isbnGenerate,"sudha");
        //deserilaztion of json to object of class
        AddBookResponse addBookResponse=response.body().as(AddBookResponse.class);
        Assert.assertEquals(addBookResponse.getID(),ExpectedResult,"ID is mismatching");

    }
}
