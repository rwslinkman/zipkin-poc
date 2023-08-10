package be.telenet.iss.poc.postcardservice.client.homeaddress.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String streetName;
    private String houseNumber;
    private String postCode;
    private String city;
    private String country;
}
