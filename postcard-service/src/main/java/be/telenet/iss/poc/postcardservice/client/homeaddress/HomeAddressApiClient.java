package be.telenet.iss.poc.postcardservice.client.homeaddress;

import be.telenet.iss.poc.postcardservice.client.homeaddress.model.AddressResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class HomeAddressApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public AddressResponse getUserAddress(String userName) {
        log.info("Incoming request");
        ResponseEntity<AddressResponse> response = restTemplate.getForEntity("http://address-service:8080/address?user=" + userName, AddressResponse.class);
        return response.getBody();
    }
}
