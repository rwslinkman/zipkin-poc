package be.telenet.iss.poc.postcardservice.client.postnl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMailRequest {

    private String recipientLine;
    private String addressLine;
    private String locationLine;
    private String stampId;
    private String content;
}
