package be.telenet.iss.poc.postcardservice.client.stamp;

import be.telenet.iss.poc.postcardservice.client.stamp.model.BuyStampRequest;
import be.telenet.iss.poc.postcardservice.client.stamp.model.BuyStampResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostStampApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String buyStamp(String userName) {
        var payload = new BuyStampRequest(userName);
        ResponseEntity<BuyStampResponse> response = restTemplate.postForEntity("http://stampservice:9090/buy-stamp", payload, BuyStampResponse.class);
        return response.getBody().getStampId();
    }
}
