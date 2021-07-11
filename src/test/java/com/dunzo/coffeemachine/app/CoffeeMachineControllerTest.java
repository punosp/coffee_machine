package com.dunzo.coffeemachine.app;

import com.dunzo.coffeemachine.CoffeeMachineApplication;
import com.dunzo.coffeemachine.model.CoffeeMachineRequest;
import com.dunzo.coffeemachine.model.CoffeeMachineResponse;
import com.dunzo.coffeemachine.model.ImmutableCoffeeMachineResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CoffeeMachineApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoffeeMachineControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private CoffeeMachineRequest coffeeMachineRequest;

    @BeforeEach
    public void setUp() {
//        Object obj = parser.parse(new FileReader("/Users/Shared/crunchify.json"));

        ObjectMapper mapper = new ObjectMapper();
        Jdk8Module j = new Jdk8Module();
        j.configureAbsentsAsNulls(true);//tried using true and false too
        mapper.registerModule(j);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            coffeeMachineRequest = mapper.readValue(new File("/Users/ashutosh/Desktop/flipkart/coffee-machine-master 2/src/test/resources/input.json"), CoffeeMachineRequest.class);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testMakeBeverages() {

        List<String> messages = Arrays.asList(
                "hotTea is prepared",
                "hotCoffee is prepared",
                "greenTea cannot be prepared because greenMixture are not available and sugarSyrup are not sufficient",
                "blackTea cannot be prepared because sugarSyrup and hotWater are not sufficient");

        CoffeeMachineResponse expected = ImmutableCoffeeMachineResponse.builder().messages(messages).build();

        ResponseEntity<CoffeeMachineResponse> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/v1/beverages", coffeeMachineRequest, CoffeeMachineResponse.class);

        CoffeeMachineResponse actual = responseEntity.getBody();

        assertThat(actual).isEqualTo(expected);

    }
}