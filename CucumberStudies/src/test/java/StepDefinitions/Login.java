package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Login {
    @Given("go to url")
    public void go_to_url() {
        System.out.println("land on page successfully");
    }
    @When("enter email and password")
    public void enter_email_and_password() {
        System.out.println("email and password entered successfully");
    }
    @Then("verify that user logged in successfully")
    public void verify_that_user_logged_in_successfully() {

    }

}
