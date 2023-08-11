package be.telenet.iss.poc.homeaddressservice.controller;

import be.telenet.iss.poc.homeaddressservice.model.Address;
import be.telenet.iss.poc.homeaddressservice.model.AddressResponse;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class HomeAddressController
{
    private static final Map<String, AddressResponse> addressesDatabase = Map.of(
        "person1", new AddressResponse("Person One" , new Address("First Street", "1a", "1234AB", "Eindhoven", "Netherlands")),
        "person2", new AddressResponse("Person Two", new Address("Second Street", "1b", "4321AB", "Eindhoven", "Netherlands"))
    );

    private final int failureChangePercentage = 5;
    private final Random random = new Random();

    @GetMapping(path ="/address")
    public ResponseEntity<AddressResponse> getUserAddress(@RequestParam("user") String username) {
        log.info("Incoming request");

        if(randomChance() < failureChangePercentage) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT.value()).build();
        }

        if(!addressesDatabase.containsKey(username)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressesDatabase.get(username));
    }

    private int randomChance() {
        return random.nextInt(100);
    }
}
