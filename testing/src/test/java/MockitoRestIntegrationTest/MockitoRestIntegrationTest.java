package MockitoRestIntegrationTest;

import app.App;
import app.infrastructure.SmsSender;
import app.infrastructure.SmsSenderException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by marc on 05/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {App.class, IntegrationTestConfiguration.class})
@ActiveProfiles("integrationTest")
public class MockitoRestIntegrationTest {
    
    @Autowired
    @Qualifier("smsClient")
    private HttpClient httpClient;
    
    @Autowired
    private SmsSender smsSender;
    
    private boolean smsHasBeenSent = false;

    @Before
    public void before() throws IOException {

        Answer<HttpResponse> httpResponseAnswer = new Answer<HttpResponse>() {
            @Override
            public HttpResponse answer(InvocationOnMock invocation) throws Throwable {
                StatusLine statusLine = new BasicStatusLine(HttpVersion.HTTP_1_1, 200, "");
                HttpResponse httpResponse = new BasicHttpResponse(statusLine);
                BasicHttpEntity httpEntity = new BasicHttpEntity();

                httpEntity.setContent(new ByteArrayInputStream("OK".getBytes()));
                httpResponse.setEntity(httpEntity);

                smsHasBeenSent = true;

                return httpResponse;
            }
        };

        Mockito.when(httpClient.execute(Mockito.any(HttpPost.class))).thenAnswer(httpResponseAnswer);

    }
    
    @Test
    public void testRestSender() throws IOException, SmsSenderException {
        
        smsSender.sendSms("31612345678", "Me", "Dit is een test");
        
        Assert.assertThat(smsHasBeenSent, Matchers.is(true));
    }

    @After
    public void after() {
        // Deze is belangrijk als met dezelfde instantie van de mock nog een test gedaan wordt
        Mockito.reset(httpClient);
    }
    
}
