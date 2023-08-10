package be.telenet.iss.poc.postnlservice.controller;

import be.telenet.iss.poc.postnlservice.client.stamp.PostStampApiClient;
import be.telenet.iss.poc.postnlservice.model.SendMailRequest;
import be.telenet.iss.poc.postnlservice.model.SendMailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MailController {

    private final static ArrayList<SendMailRequest> receivedRequests = new ArrayList<>();

    private final PostStampApiClient stampApiClient;

    @Autowired
    public MailController(PostStampApiClient stampApiClient) {
        this.stampApiClient = stampApiClient;
    }

    @PostMapping(path = "/sendmail")
    public ResponseEntity<SendMailResponse> sendMail(@RequestBody SendMailRequest request) {
        var isValidStamp = stampApiClient.validateStamp(request.getStampId());
        if(!isValidStamp) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED.value()).build();
        }

        var currentSize = receivedRequests.size();
        receivedRequests.add(request);
        return ResponseEntity.ok(new SendMailResponse(currentSize + 1));
    }
}
