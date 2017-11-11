package se.andolf.lift.auth.rest;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import se.andolf.lift.auth.config.Client;
import se.andolf.lift.auth.config.Environment;
import se.andolf.lift.auth.utils.FileUtils;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbConfigurationBuilder.mongoDb;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author Thomas on 2017-11-11.
 */
public class TokenIT {

    private static final String CLIENT_USER_NAME = "ios";
    private static final String CLIENT_PASS = "secret";
    private static final String TOKEN_RESOURCE = "/oauth/token";
    private static final String CHECK_TOKEN_RESOURCE = "/oauth/check_token";
    private static final String TOKEN_KEY_RESOURCE = "/oauth/token_key";


    @BeforeClass
    public static void init() {
        Client.init();
    }

    @Rule
    public MongoDbRule remoteMongoDbRule = new MongoDbRule(mongoDb().databaseName("lift").host(Environment.current().host()).build());

    @Test
    public void shouldGet401IfBadClientCredentials() {
        given()
                .auth()
                .preemptive()
                .basic("myClient", "MyPassword")
        .accept(ContentType.JSON)
        .post(TOKEN_RESOURCE)
        .then()
        .statusCode(401);
    }

    @Test
    @UsingDataSet(locations="/db/user.json", loadStrategy= LoadStrategyEnum.CLEAN_INSERT)
    public void shouldGet200IfCorrectCredentials() {
        given()
                .auth()
                .preemptive()
                .basic(CLIENT_USER_NAME, CLIENT_PASS)
                .accept(ContentType.JSON)
                .param("password", "password")
                .param("username", "john.doe@gmail.com")
                .param("scope", "user")
                .param("grant_type", "password")
                .post(TOKEN_RESOURCE)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGet400IfBadCredentials() {
        given()
                .auth()
                .preemptive()
                .basic(CLIENT_USER_NAME, CLIENT_PASS)
                .accept(ContentType.JSON)
                .param("password", "1")
                .param("username", "1")
                .param("scope", "user")
                .param("grant_type", "password")
                .post(TOKEN_RESOURCE)
                .then()
                .statusCode(400);
    }

    @Test
    @UsingDataSet(locations="/db/user.json", loadStrategy= LoadStrategyEnum.CLEAN_INSERT)
    public void shouldGet200IfSupplyingValidToken() {
        final String token = given()
                .auth()
                .preemptive()
                .basic(CLIENT_USER_NAME, CLIENT_PASS)
                .accept(ContentType.JSON)
                .param("password", "password")
                .param("username", "john.doe@gmail.com")
                .param("scope", "user")
                .param("grant_type", "password")
                .post(TOKEN_RESOURCE)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .get("access_token");

        given()
                .auth()
                .preemptive()
                .basic(CLIENT_USER_NAME, CLIENT_PASS)
                .accept(ContentType.JSON)
                .param("token", token)
                .post(CHECK_TOKEN_RESOURCE)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGet400IfSupplyingNonValidToken() {
        final String token = "1234567890";

        given()
                .auth()
                .preemptive()
                .basic(CLIENT_USER_NAME, CLIENT_PASS)
                .accept(ContentType.JSON)
                .param("token", token)
                .post(CHECK_TOKEN_RESOURCE)
                .then()
                .statusCode(400);
    }

    @Test
    public void shouldGet200AndThePublicKey() {
        final String publicKey = FileUtils.read("keystore/publicKey.txt");

        given()
                .auth()
                .preemptive()
                .basic(CLIENT_USER_NAME, CLIENT_PASS)
                .accept(ContentType.JSON)
                .get(TOKEN_KEY_RESOURCE)
                .then()
                .statusCode(200)
                .body("alg", is("SHA256withRSA"))
                .body("value", is(publicKey));
    }
}
