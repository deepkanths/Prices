package Prices;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class StepDefinitions {

    public static io.restassured.specification.RequestSpecification req;
    public static Response response;
    public static String date;
    public static String responseBody;
    public static String URI = "https://api.exchangeratesapi.io";




    public RequestSpecification requestSpecification() throws IOException
    {

        if(req==null)
        {
            PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
            req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;


    }

    public static String getGlobalValue(String key) throws IOException
    {
        Properties prop =new Properties();
        FileInputStream fis =new FileInputStream("src\\test\\java\\Resources\\global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    @Given("I set the base URL as {string}")
    public void i_set_the_base_URL_as(String url) throws FileNotFoundException {

        System.out.println("Inside Given i_set_the_base_URL_as");
//        PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
//        req=new RequestSpecBuilder().setBaseUri(url)
//                .addFilter(RequestLoggingFilter.logRequestTo(log))
//                .addFilter(ResponseLoggingFilter.logResponseTo(log))
//                .setContentType(ContentType.JSON).build();

         RestAssured.baseURI = url;

    }

    @When("I make API GET call using URL {string}")
    public void i_make_API_GET_call_using_URL(String url) {
//        RestAssured.baseURI = URI;
        System.out.println("Inside When i_set_the_base_URL_as");
        date = url;

    }

    @When("I set date as {string} and GET Exchange Rates")
    public void i_set_date_as(String dt) {
        date = date+"/"+dt;
        req = RestAssured.given();
        response = req.request(Method.GET, date);
        responseBody = response.getBody().asString();
        System.out.println("Response Body is => " + responseBody);

    }

    @Then("I verify API response status code")
    public void i_verify_API_response_status_code() {

        System.out.println("Response code is: "+response.getStatusCode());
    }


    //    RestAssured.baseURI = "https://api.exchangeratesapi.io";
//
//    RequestSpecification httpReq = RestAssured.given();
//    Response response = httpReq.request(Method.GET, "/api");
//
//    String responseBody = response.getBody().asString();
//        System.out.println("Response Body is => " +responseBody);
//
//        System.out.println("Exiting main......");


}
