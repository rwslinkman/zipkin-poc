package be.telenet.iss.poc.postcardservice.client.stamp;

import be.telenet.iss.poc.postcardservice.client.stamp.model.BuyStampRequest;
import be.telenet.iss.poc.postcardservice.client.stamp.model.BuyStampResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostStampApiClient {

    private final RestTemplate restTemplate;

    @Autowired
    public PostStampApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String buyStamp(String userName) {
        var payload = new BuyStampRequest(userName);
        ResponseEntity<BuyStampResponse> response = restTemplate.postForEntity("http://stamp-service:8080/buy-stamp", payload, BuyStampResponse.class);
        return response.getBody().getStampId();
    }
}
