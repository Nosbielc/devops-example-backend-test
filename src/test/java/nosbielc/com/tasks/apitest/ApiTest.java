package nosbielc.com.tasks.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 24/05/2020
 * @project devops-example-backend-test
 */
public class ApiTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:7000/tasks-backend";
    }

    @Test
    public void deveRetornarTarefas() {
        RestAssured
                .given()
                .when()
                .get("/todo")
                .then()
                .statusCode(200);
    }

    @Test
    public void deveAdicionarTarefaComSucesso() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{ \"task\" : \"Teste via api\", \"dueDate\" : \"2020-12-30\" }")
                .when()
                .post("/todo")
                .then()
                .statusCode(201);
    }

    @Test
    public void naoDeveAdicionarTarefaInvalida() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{ \"task\" : \"Teste via api\", \"dueDate\" : \"2010-12-30\" }")
                .when()
                .post("/todo")
                .then()
                .statusCode(400)
                .body("message", CoreMatchers.is("Due date must not be in past"));
    }

}
