package app.infrastructure;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SmsSender {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String OPERATION = "send";
    private static final String ROUTE = "537";
    private static final String OK = "OK";

    private final String smsUsername = "username";
    private final String smsPassword = "password";

    private final HttpClient httpClient;

    @Autowired
    public SmsSender(@Qualifier("smsClient") HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void sendSms(String destinationPhoneNumber, String sender, String body) throws IOException, SmsSenderException {
       HttpPost httpPost = new HttpPost("https://www.smssender.nl/send.php");

        httpPost.addHeader("User-Agent", USER_AGENT);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("OPERATION", OPERATION));
        urlParameters.add(new BasicNameValuePair("USERNAME", smsUsername));
        urlParameters.add(new BasicNameValuePair("PASSWORD", smsPassword));
        urlParameters.add(new BasicNameValuePair("DESTINATION", destinationPhoneNumber));
        urlParameters.add(new BasicNameValuePair("SENDER", sender));
        urlParameters.add(new BasicNameValuePair("ROUTE", ROUTE));
        urlParameters.add(new BasicNameValuePair("BODY", body));
        httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = httpClient.execute(httpPost);

        String responseCode = new BasicResponseHandler().handleEntity(response.getEntity());

        if(!responseCode.equals(OK)) {
            throw new SmsSenderException(responseCode);
        }
    }

}
