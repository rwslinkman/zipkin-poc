package be.telenet.iss.poc.poststampservice.controller;

import be.telenet.iss.poc.poststampservice.model.BuyStampRequest;
import be.telenet.iss.poc.poststampservice.model.BuyStampResponse;
import be.telenet.iss.poc.poststampservice.model.UserBalance;
import be.telenet.iss.poc.poststampservice.model.ValidateStampRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class StampsController {

    private final static List<UserBalance> userBalance = List.of(
            new UserBalance("person1", 10),
            new UserBalance("person2", 10)
    );
    private final static ArrayList<String> stampCollection = new ArrayList<>();

    @PostMapping(path = "/buy-stamp")
    public ResponseEntity<BuyStampResponse> buyStamp(@RequestBody BuyStampRequest request) {
        var foundUserBalance = userBalance.stream().filter(it -> it.getAccountOwner().equals(request.getUser())).findFirst();
        if(foundUserBalance.isEmpty()) {
            // No account for user
            return ResponseEntity.notFound().build();
        }

        var userBalance = foundUserBalance.get();
        if(userBalance.getBalance() > 0) {
            // User is allowed to buy stamp because he has positive balance
            var stampId = generateStampId();
            stampCollection.add(stampId);
            userBalance.reduceBalance();
            // Respond with generated stamp
            var response = new BuyStampResponse(stampId, userBalance);
            return ResponseEntity.ok(response);
        } else {
            // User has no credit to buy stamps
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED.value()).build();
        }
    }

    @PostMapping(path = "/validate")
    public ResponseEntity<Object> validateStamp(@RequestBody ValidateStampRequest request) {
        if(stampCollection.contains(request.getStampId())) {
            // Stamp was found, cannot be used again
            stampCollection.remove(request.getStampId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    private String generateStampId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
