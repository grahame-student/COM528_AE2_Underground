package uk.ac.solent.lunderground.jaxbdao;

import org.junit.Test;
import uk.ac.solent.lunderground.model.dao.TicketDao;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.dto.Ticket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TicketDaoJaxbTest
{
    private static final String EXPECTED_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                               "<Ticket>\n" +
                                               "    <validationCode>fpHTNa010ese9ljcj2NZW6Zc9pk6xlb91t1DrgAs4IISLlQFAYjt9RhFaxqTCGJ3j5WEwR9mFVhQmr/xjpLUl7whdB9zZ429x0sUR2SLoo+OmwYTgQ8QQGmMpiFtPVO+XT6nKylwAqHIhmoO6vfHgx73buExPdtUJZya/WUganY=</validationCode>\n" +
                                               "</Ticket>\n";
    private static final String VALID_XML    = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                               "<Ticket>\n" +
                                               "    <validationCode>fpHTNa010ese9ljcj2NZW6Zc9pk6xlb91t1DrgAs4IISLlQFAYjt9RhFaxqTCGJ3j5WEwR9mFVhQmr/xjpLUl7whdB9zZ429x0sUR2SLoo+OmwYTgQ8QQGmMpiFtPVO+XT6nKylwAqHIhmoO6vfHgx73buExPdtUJZya/WUganY=</validationCode>\n" +
                                               "</Ticket>\n";
    private static final String INVALID_XML  = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                               "<Ticket>\n" +
                                               "    <validationCode>gpHTNa010ese9ljcj2NZW6Zc9pk6xlb91t1DrgAs4IISLlQFAYjt9RhFaxqTCGJ3j5WEwR9mFVhQmr/xjpLUl7whdB9zZ429x0sUR2SLoo+OmwYTgQ8QQGmMpiFtPVO+XT6nKylwAqHIhmoO6vfHgx73buExPdtUJZya/WUganY=</validationCode>\n" +
                                               "</Ticket>\n";

    @Test
    public void encodeTicketReturnsXmlRepresentationOfPassedInTicket()
    {
        TicketDao ticketDao = new TicketDaoJaxb();

        String xml = ticketDao.encodeTicket(new Ticket());

        assertThat(xml, equalTo(EXPECTED_XML));
    }

    @Test
    public void decodeTicketReturnsTicketFromPassedInXml()
    {
        TicketDao ticketDao = new TicketDaoJaxb();
        Ticket ticket = new Ticket();
        String ticketXml = ticketDao.encodeTicket(ticket);

        Ticket decodedTicket = ticketDao.getTicket(ticketXml);

        assertThat(decodedTicket, equalTo(ticket));
    }

    @Test
    public void validateTicketReturnsTrueWhenTicketHasValidValidationCode()
    {
        TicketDao ticketDao = new TicketDaoJaxb();
        Ticket decodedTicket = ticketDao.getTicket(VALID_XML);

        assertThat(ticketDao.validateTicket(decodedTicket), equalTo(true));
    }

    @Test
    public void validateTicketReturnsTrueWhenTicketHasInvalidValidationCode()
    {
        TicketDao ticketDao = new TicketDaoJaxb();
        Ticket decodedTicket = ticketDao.getTicket(INVALID_XML);

        assertThat(ticketDao.validateTicket(decodedTicket), equalTo(false));
    }
}
