package app;

import com.icegreen.greenmail.junit.GreenMailRule;
import org.junit.Rule;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by marc on 01/04/16.
 */
public class GreenMailTest {

    @Rule
    public final GreenMailRule greenMailRule = new GreenMailRule();

    @Test
    public void testMailSending() throws MessagingException {

        // Given
        Session session = greenMailRule.getSmtp().createSession();

        // When
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("test@test.com"));
        msg.addRecipient(Message.RecipientType.TO,
                new InternetAddress("bar@example.com"));
        msg.setSubject("Email sent to GreenMail via plain JavaMail");
        msg.setText("Fetch me via IMAP");
        Transport.send(msg);

        // Then
        MimeMessage[] receivedMessages = greenMailRule.getReceivedMessages();
        assertThat(receivedMessages.length, is(1));
        assertThat(receivedMessages[0].getSubject(),
                is("Email sent to GreenMail via plain JavaMail"));
    }
}
