package be.telenet.iss.poc.postnlservice.controller;

import be.telenet.iss.poc.postnlservice.client.stamp.PostStampApiClient;
import be.telenet.iss.poc.postnlservice.model.SendMailRequest;
import be.telenet.iss.poc.postnlservice.model.SendMailResponse;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
public class MailController {

    private static final ArrayList<SendMailRequest> receivedRequests = new ArrayList<>();

    private final PostStampApiClient stampApiClient;

    private final int failureChangePercentage = 40;
    private final Random random = new Random();

    @Autowired
    public MailController(PostStampApiClient stampApiClient) {
        this.stampApiClient = stampApiClient;
    }

    @PostMapping(path = "/sendmail")
    public ResponseEntity<SendMailResponse> sendMail(@RequestBody SendMailRequest request) {
        log.info("Incoming request");
        if (randomChance() < failureChangePercentage) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT.value()).build();
        }

        try {
            var isValidStamp = stampApiClient.validateStamp(request.getStampId());
            if (!isValidStamp) {
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED.value()).build();
            }

            var currentSize = receivedRequests.size();
            receivedRequests.add(request);
            return ResponseEntity.ok(new SendMailResponse(currentSize + 1));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    private int randomChance() {
        return random.nextInt(100);
    }
}
