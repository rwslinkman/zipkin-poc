package be.telenet.iss.poc.postcardservice.controller;

import be.telenet.iss.poc.postcardservice.client.homeaddress.HomeAddressApiClient;
import be.telenet.iss.poc.postcardservice.client.postnl.PostNLApiClient;
import be.telenet.iss.poc.postcardservice.client.postnl.model.SendMailRequest;
import be.telenet.iss.poc.postcardservice.client.stamp.PostStampApiClient;
import be.telenet.iss.poc.postcardservice.model.MailPostcardRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PostcardController {

    private final HomeAddressApiClient homeAddressClient;
    private final PostStampApiClient stampApiClient;
    private final PostNLApiClient postNLApiClient;

    @Autowired
    public PostcardController(HomeAddressApiClient homeAddressClient, PostStampApiClient stampApiClient, PostNLApiClient postNLApiClient) {
        this.homeAddressClient = homeAddressClient;
        this.stampApiClient = stampApiClient;
        this.postNLApiClient = postNLApiClient;
    }

    @PostMapping(path = "/send-postcard")
    public ResponseEntity<Object> sendPostcard(@RequestBody MailPostcardRequest request) {
        log.info("Incoming request");
        try {
            var address = homeAddressClient.getUserAddress(request.getRecipient());
            var stampId = stampApiClient.buyStamp(request.getSender());

            var addr = address.getAddress();
            var postcardRequest = new SendMailRequest(
                address.getRecipientName(),
                String.format("%s %s", addr.getStreetName(), addr.getHouseNumber()),
                String.format("%s %s, %s", addr.getPostCode(), addr.getCity(), addr.getCountry()),
                stampId,
                request.getCardText()
            );

            var response = postNLApiClient.sendMail(postcardRequest);
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT.value()).build();
        }
    }
}
