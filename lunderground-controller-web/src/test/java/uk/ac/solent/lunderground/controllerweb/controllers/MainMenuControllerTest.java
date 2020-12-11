package uk.ac.solent.lunderground.controllerweb.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ContextConfiguration(
        locations =
        {
                "classpath:spring/application-context.xml",
                "classpath:spring/Lunderground-servlet.xml"
        }
)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MainMenuControllerTest
{
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void Setup()
    {
        mockMvc = webAppContextSetup(this.context).build();
    }

    /**
     * Check that getMainPage returns the intended landing page when the root endpoint is requested.
     */
    @Test
    public void getMainPageReturnsIndex() throws Exception
    {
        mockMvc.perform(get("/"))
               .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
    }
}
