package be.telenet.iss.poc.postcardservice.client.postnl;

import be.telenet.iss.poc.postcardservice.client.postnl.model.SendMailRequest;
import be.telenet.iss.poc.postcardservice.client.postnl.model.SendMailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostNLApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public int sendMail(SendMailRequest request) {
        ResponseEntity<SendMailResponse> response =   restTemplate.postForEntity("http://postnlservice:9092/sendmail", request, SendMailResponse.class);
        return response.getBody().getQueuePosition();
    }
}