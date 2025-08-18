package com.epam.oneendpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.imds.Ec2MetadataClient;
import software.amazon.awssdk.imds.Ec2MetadataResponse;

@RestController
public class SimpleController {

    private static final String BASE_METADATA_URL = "/latest/meta-data/placement";

    @GetMapping("/metadata")
    public ResponseEntity<String> getMetadata() {
        try (Ec2MetadataClient client = Ec2MetadataClient.create()) {
            Ec2MetadataResponse regionResponse = client.get(BASE_METADATA_URL + "/region");
            String region = regionResponse.asString();

            Ec2MetadataResponse response = client.get(BASE_METADATA_URL + "/availability-zone");
            String availabilityZone = response.asString();
            return ResponseEntity.ok("Region: [" + region + "], AZ: [" + availabilityZone + "]");
        }
    }
}
