package be.telenet.iss.poc.postcardservice.client.homeaddress.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private String recipientName;
    private Address address;
}
