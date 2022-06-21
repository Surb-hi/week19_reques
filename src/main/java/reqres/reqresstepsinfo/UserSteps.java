package reqres.reqresstepsinfo;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import reqres.constants.EndPoints;
import reqres.model.UserPojo;

import java.util.HashMap;

public class UserSteps {
    @Step("Creating Users with perameter")
    public ValidatableResponse createUsers(String name,String job) {
        UserPojo userPojo=new UserPojo();
        userPojo.setName(name);
        userPojo.setJob(job);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }
    @Step("Getting the Users information from Id")
    public HashMap<String, Object> getCreatedUsersId(String usersId) {

        HashMap<String, Object> usersMap = SerenityRest.given().log().all()
                .when()
                .pathParam("usersId",usersId)
                .get(EndPoints.CREATED_USER_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return usersMap;
    }
    @Step("Update users with perameter")
    public ValidatableResponse updateusers(String usersId,String name,String job) {
            UserPojo userPojo=new UserPojo();
            userPojo.setName(name);
            userPojo.setJob(job);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(userPojo)
                .when()
                .pathParam("usersId",usersId)
                .patch(EndPoints.UPDATE_USER_ID)
                .then();
    }

    @Step("Deleting Users information with UsersId")
    public ValidatableResponse deleteUsers(String usersId) {
        return SerenityRest.given().log().all()
                .pathParam("usersId",usersId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }

    @Step("Getting UsersId information with ")
    public ValidatableResponse getServicesById(String usersId) {
        return SerenityRest.given().log().all()
                .pathParam("usersId",usersId)
                .when()
                .get(EndPoints.GET_ALL_USER)
                .then();
    }
    @Step("Getting All users information")
    public ValidatableResponse getAllUsers(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_USER)
                .then();
    }
}
