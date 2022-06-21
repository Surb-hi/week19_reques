package reqres.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import reqres.reqresstepsinfo.UserSteps;
import reqres.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyStepdefs {

    static String name = "name" + TestUtils.getRandomValue();
    static String job = "job" + TestUtils.getRandomValue();
    static String usersId;
    @Steps
    UserSteps userSteps;
    static ValidatableResponse response;

    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {
        response = userSteps.getAllUsers();
    }

    @Then("^User get back a valid status code (\\d+)$")
    public void userGetBackAValidStatusCode(int code) {

        response.log().all().statusCode(code);
    }

    @When("^User sends a Post request to list endpoint$")
    public void userSendsAPostRequestToListEndpoint() {
        response = userSteps.createUsers(name, job);
        response.log().all().statusCode(201);
        usersId = response.log().all().extract().path("id");
    }

    @And("^Verify that user created sucessfully$")
    public void verifyThatUserCreatedSucessfully() {
        HashMap<String, Object> UsersMap = userSteps.getCreatedUsersId(usersId);
        Assert.assertThat(UsersMap, hasValue(name));
        System.out.println(usersId);
    }

    @When("^User sends a patch request to list endpoint$")
    public void userSendsAPatchRequestToListEndpoint() {
        name = "name" + TestUtils.getRandomValue();
        ValidatableResponse response =userSteps.updateusers(usersId,name,job);
    }

    @And("^Verify that user Updated sucessfully$")
    public void verifyThatUserUpdatedSucessfully() {
        HashMap<String, Object> usersMap =userSteps.getCreatedUsersId(usersId);
        Assert.assertThat(usersMap, hasValue(name));
        System.out.println(usersId);
    }

    @When("^User sends a delete request to list endpoint$")
    public void userSendsADeleteRequestToListEndpoint() {
        userSteps.deleteUsers(usersId);
        userSteps.getServicesById(usersId);
    }
    }

