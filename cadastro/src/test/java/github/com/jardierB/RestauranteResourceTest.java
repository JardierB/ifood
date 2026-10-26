package github.com.jardierB;

import static io.restassured.RestAssured.given;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import org.approvaltests.Approvals;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;

import github.com.jardierB.model.Restaurante;

@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
public class RestauranteResourceTest {

    @Test 
    @DataSet("restaurantes-cenario-1.yml")
    public void buscarRestaurantes() {
        String resultado = given()
                .when().get("/restaurantes")
                .then()
                .statusCode(200)
                .extract().asString();

        Approvals.verifyJson(resultado);
    }

    private RequestSpecification given() {
        return RestAssured.given().contentType(ContentType.JSON);
    }

    @Test 
    @DataSet("restaurantes-cenario-1.yml")
    public void alterarRestaurante() {
        Restaurante dto = new Restaurante();
        dto.nome = "Novo nome";
        Long parametroValue = 123L;

        given()
        .with().pathParam("id", parametroValue)
        .body(dto)
        .when().put("restaurantes/{id}")
        .then()
        .statusCode(StatusCode.NOT_FOUND)
        .extract().asString();

        Restaurante findById = Restaurante.findById(parametroValue);
        Assert.assertEquals(dto.nome, findById.nome);
       
    }

   

}
