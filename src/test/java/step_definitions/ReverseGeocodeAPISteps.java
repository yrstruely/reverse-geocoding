package step_definitions;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;


public class ReverseGeocodeAPISteps {

    private RequestSpecification request;
    private ValidatableResponse response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
    }



    @Given("^I am on the RSRP Look up API$")
    public void i_am_on_the_RSRP_Look_up_API() throws Exception {

        String BASE_URI = "http://staging.niems.nzta.govt.nz/xy_sh";

        request.baseUri(BASE_URI)
                .log().method()
                .log().uri()
                .log().params()
                .log().headers();
    }

    @When("^I make a get request for \"([^\"]*)\" RP Point with units measured in km$")
    public void i_make_a_get_request_for_RP_Point_with_units_measured_in_km(String param) throws Exception {
        response = request.param("rp", param)
                .param("km", "true")
                .get().then()
                .log().all();
    }

    @Then("^I get a response code of (\\d+)$")
    public void i_get_a_response_code_of(int statusCode) throws Exception {
        response.statusCode(statusCode);
    }

    @Then("^the response contains \"([^\"]*)\" field with a value of \"([^\"]*)\"$")
    public void the_response_contains_field_with_a_value_of(String field, String value) throws Exception {

        String actual = response.extract().jsonPath().get(field).toString();
        Assert.assertEquals(actual, value);
    }
}
