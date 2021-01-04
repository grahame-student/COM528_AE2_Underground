package uk.ac.solent.lunderground.jaxbdao;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import uk.ac.solent.lunderground.model.dao.TicketDao;
import uk.ac.solent.lunderground.model.dto.Ticket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TicketDaoJaxbTest
{
    private static final String EXPECTED_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                               "<Ticket>\n" +
                                               "    <validationCode>fpHTNa010ese9ljcj2NZW6Zc9pk6xlb91t1DrgAs4IISLlQFAYjt9RhFaxqTCGJ3j5WEwR9mFVhQmr/xjpLUl7whdB9zZ429x0sUR2SLoo+OmwYTgQ8QQGmMpiFtPVO+XT6nKylwAqHIhmoO6vfHgx73buExPdtUJZya/WUganY=</validationCode>\n" +
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
        String validXml = ticketDao.encodeTicket(new Ticket());
        Ticket decodedTicket = ticketDao.getTicket(validXml);

        assertThat(ticketDao.validateTicket(decodedTicket), equalTo(true));
    }

    @Test
    public void validateTicketReturnsTrueWhenTicketHasInvalidValidationCode() throws TransformerException
    {
        // We cannot simply use an XML string with a pre-generated validationCode.
        // This is due to the crypto keys being generated with different values if
        // they don't already exist. This is especially problematic when using a
        // CI/CD server (e.g. github actions) that builds from a fresh start every time.

        TicketDao ticketDao = new TicketDaoJaxb();
        String validXml = ticketDao.encodeTicket(new Ticket());
        Document doc = parseXml(validXml);
        doc.getElementsByTagName("validationCode").item(0).setTextContent("invalid code");
        String invalidXml = toXml(doc);
        Ticket decodedTicket = ticketDao.getTicket(invalidXml);

        assertThat(ticketDao.validateTicket(decodedTicket), equalTo(false));
    }

    /**
     * Helper method to convert an XML string into a Document.
     *
     * @param xmlString String containing XML fragment
     * @return XML Document
     */
    private Document parseXml(String xmlString)
    {
        Document doc = null;
        try
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                                                            .newDocumentBuilder();
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(xmlString));
            doc = builder.parse(src);
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }

        return doc;
    }

    private String toXml(Document xmlDoc) throws TransformerException
    {
        String tmpString = null;
        try
        {
            DOMSource domSource = new DOMSource(xmlDoc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StreamResult result = new StreamResult(new StringWriter());
            transformer.transform(domSource, result);
            tmpString = result.getWriter().toString();
        }
        catch (TransformerException ex)
        {
            System.out.println(ex.toString());
        }

        return tmpString;
    }

    // TODO: additional tests that should be added
    //       -- check each field marshalled correctly
    //       -- check each field unmarshalled correctly
    //       -- check that changing fields in the generated XML causes validation to return false
}
