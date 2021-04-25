package marsrover.controller;

import com.equalexperts.marsrover.controller.ControllerAdvice;
import com.equalexperts.marsrover.controller.RoverController;
import com.equalexperts.marsrover.model.Position;
import com.equalexperts.marsrover.service.RoverService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.equalexperts.marsrover.model.Direction.NORTH;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoverControllerTest {
    @Mock
    private RoverService service;

    @InjectMocks
    private RoverController controller;

    @Before
    public void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(controller, new ControllerAdvice());
    }

    @Test
    public void postCommand_returnsOkAndLatestPosition_givenValidCommand(){
        when(service.processCommand("FLFFFRFLB")).thenReturn(Position.of(6,4, NORTH));

        given()
                .contentType(ContentType.TEXT)
                .body("FLFFFRFLB")
                .post("/api/command")
                .then()
                .statusCode(200)
                .body(is(equalTo("(6, 4) NORTH")));
    }

    @Test
    public void postCommand_returnsUnsupportedMediaType_givenUnsupportedMediaType(){
        given()
                .contentType(ContentType.JSON)
                .body("FLFFFRFLB")
                .post("/api/command")
                .then()
                .statusCode(415)
                .body(is(equalTo("{\"status\":415,\"message\":\"Unsupported media type, please send the command as text/plain\"}")));
    }

    @Test
    public void postCommand_returnsBadRequest_givenInvalidCommand(){
        given()
                .contentType(ContentType.TEXT)
                .body("")
                .post("/api/command")
                .then()
                .statusCode(400)
                .body(is(equalTo("{\"status\":400,\"message\":\"Please supply a valid input\"}")));
    }

    @Test
    public void postCommand_returnsBadRequest_givenUnsupportedCommand(){
        when(service.processCommand("TEST")).thenThrow(new IllegalArgumentException());

        given()
                .contentType(ContentType.TEXT)
                .body("TEST")
                .post("/api/command")
                .then()
                .statusCode(400)
                .body(is(equalTo("{\"status\":400,\"message\":\"An unsupported command received\"}")));
    }
}
