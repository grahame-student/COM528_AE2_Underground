package uk.ac.solent.lunderground.controllerweb.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import uk.ac.solent.lunderground.model.dto.TicketMachine;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ContextConfiguration(
        locations =
                {
                        "classpath:spring/controller-application-context.xml",
                        "classpath:spring/Lunderground-servlet.xml"
                }
)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class RestEndPointControllerTest
{
    private static final String SOME_UUID = "some uuid";
    /**
     * Web Application Context used to allow the test cases to run.
     */
    @Autowired
    private WebApplicationContext context;

    /**
     * MockMvc instance used to request endpoints and verify that the correct outcomes occur.
     */
    private MockMvc mockMvc;

    /**
     * Code to be executed before each test case to ensure that each one starts froms the same configuration.
     */
    @Before
    public void setup()
    {
        mockMvc = webAppContextSetup(this.context).build();
    }

    @Test
    public void getTicketMachineConfigReturnsStatusOk() throws Exception
    {
        mockMvc.perform(get("/rest/v1/ticketMachineConfig/{uuid}", "1234"))
               .andExpect(status().isOk());
    }

    @Test
    public void addTicketMachine() throws Exception
    {
        mockMvc.perform(post("/rest/v1/ticketMachine").content(""))
               .andExpect(status().isCreated());
    }
}
