package be.telenet.iss.poc.postnlservice.client.stamp;

import be.telenet.iss.poc.postnlservice.client.stamp.model.ValidateStampRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostStampApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean validateStamp(String stampId) {
        var payload = new ValidateStampRequest(stampId);
        var response = restTemplate.postForEntity("http://stampservice:9090/validate", payload, Object.class);
        return response.getStatusCode().is2xxSuccessful();
    }
}
