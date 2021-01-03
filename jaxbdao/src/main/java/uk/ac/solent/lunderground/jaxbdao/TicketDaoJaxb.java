package uk.ac.solent.lunderground.jaxbdao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.solent.lunderground.crypto.AsymmetricCryptography;
import uk.ac.solent.lunderground.model.dao.TicketDao;
import uk.ac.solent.lunderground.model.dto.Ticket;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.PrivateKey;

public class TicketDaoJaxb implements TicketDao
{
    /**
     * Logger instance for the TicketDaoJaxb implementation.
     */
    private static final Logger LOG = LogManager.getLogger(TicketDaoJaxb.class);

    private static final String PUBLIC_KEY_FILE = "publicKey";
    private static final String PRIVATE_KEY_FILE = "privateKey";

    @Override
    public String encodeTicket(Ticket ticket)
    {
        try
        {
            //marshal ticket to xml
            JAXBContext jaxbContext = JAXBContext.newInstance(Ticket.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(ticket, sw1);

            String ticketXml = sw1.toString();
            LOG.debug("Ticket to encode: \n" + ticketXml);

            // hash the ticket
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(ticketXml.getBytes());
            byte[] digest = md.digest();
            String ticketXmlHash = DatatypeConverter.printHexBinary(digest)
                                                    .toUpperCase();
            LOG.debug("Unencoded ticket MD5 hash: \n" + ticketXmlHash);

            // encode the hash using private key
            AsymmetricCryptography ac = new AsymmetricCryptography();
            PrivateKey privateKey = ac.getPrivateFromClassPath(PRIVATE_KEY_FILE);

            String encodedTicketHash = ac.encryptText(ticketXmlHash, privateKey);

            LOG.debug("Encrypted ticket MD5 hash: " + encodedTicketHash);

            ticket.setValidationCode(encodedTicketHash);

            StringWriter ticketXmlEncoded = new StringWriter();
            jaxbMarshaller.marshal(ticket, ticketXmlEncoded);

            LOG.debug("ticket with encrypted hash: " + ticketXmlEncoded.toString());
            return ticketXmlEncoded.toString();
        }
        catch (Exception ex)
        {
            LOG.error("problem encrypting ticket", ex);
        }
        return null;
    }

    @Override
    public boolean validateTicket(String encodedTicket)
    {
        return false;
    }
}
