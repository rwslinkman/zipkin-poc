package be.telenet.iss.poc.postcardservice.client.homeaddress;

import be.telenet.iss.poc.postcardservice.client.homeaddress.model.AddressResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HomeAddressApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public AddressResponse getUserAddress(String userName) {
        ResponseEntity<AddressResponse> response = restTemplate.getForEntity("http://address-service:8080/address?user=" + userName, AddressResponse.class);
        return response.getBody();
    }
}
