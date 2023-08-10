package be.telenet.iss.poc.postcardservice.client.stamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateStampRequest {
    private String stampId;
}
