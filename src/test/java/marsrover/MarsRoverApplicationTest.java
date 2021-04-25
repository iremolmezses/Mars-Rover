package marsrover;

import com.equalexperts.marsrover.MarsRoverApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarsRoverApplication.class)
@AutoConfigureMockMvc
public class MarsRoverApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postCommand_returnsOkAndLatestPosition_givenValidCommand() throws Exception {
        mockMvc.perform(post("/api/command")
                .contentType("text/plain")
                .content("FLFFFRFLB"))
                .andExpect(status().isOk())
                .andExpect(content().string("(6, 4) NORTH"));
    }

    @Test
    public void postCommand_returnsUnsupportedMediaType_givenUnsupportedMediaType() throws Exception {
        mockMvc.perform(post("/api/command")
                .contentType("application/json")
                .content("FLFFFRFLB"))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(content().string("{\"status\":415,\"message\":\"Unsupported media type, please send the command as text/plain\"}"));
    }

    @Test
    public void postCommand_returnsBadRequest_givenInvalidCommand() throws Exception {
        mockMvc.perform(post("/api/command")
                .contentType("text/plain")
                .content(""))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"status\":400,\"message\":\"Please supply a valid input\"}"));
    }

    @Test
    public void postCommand_returnsBadRequest_givenUnsupportedCommand() throws Exception {
        mockMvc.perform(post("/api/command")
                .contentType("text/plain")
                .content("TEST"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"status\":400,\"message\":\"An unsupported command received\"}"));
    }
}
