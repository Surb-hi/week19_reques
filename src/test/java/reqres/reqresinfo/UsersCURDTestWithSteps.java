package reqres.reqresinfo;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import reqres.reqresstepsinfo.UserSteps;
import reqres.testbase.TestBase;
import reqres.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UsersCURDTestWithSteps extends TestBase {
    static String name = "name" + TestUtils.getRandomValue();
    static String job = "job" + TestUtils.getRandomValue();
    static String usersId;
    @Steps
    UserSteps userSteps;
    static ValidatableResponse response;

    @Title("This will create a new Users ")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUsers(name, job);
        response.log().all().statusCode(201);
        usersId = response.log().all().extract().path("id");
    }

    @Title("Verify that the Users added in to stack")
    @Test
    public void test002() {
        HashMap<String, Object> UsersMap = userSteps.getCreatedUsersId(usersId);
        Assert.assertThat(UsersMap, hasValue(name));
        System.out.println(usersId);
    }
    @Title("This will Updated created id")
    @Test
    public void test003() {
       name = "name" + TestUtils.getRandomValue();

        ValidatableResponse response =userSteps.updateusers(usersId,name,job);
        response.log().all().statusCode(200);
        HashMap<String, Object> usersMap =userSteps.getCreatedUsersId(usersId);
        Assert.assertThat(usersMap, hasValue(name));
        System.out.println(usersId);
    }

    @Title("This will Delate created id")
    @Test
    public void test004() {
        userSteps.deleteUsers(usersId).statusCode(200);
        userSteps.getServicesById(usersId).statusCode(404);
    }

}
