package com.epam.oneendpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.imds.Ec2MetadataClient;
import software.amazon.awssdk.imds.Ec2MetadataResponse;

@RestController
public class SimpleController {

    @GetMapping("/metadata")
    public ResponseEntity<String> getMetadata() {
        try (Ec2MetadataClient client = Ec2MetadataClient.create()) {
            Ec2MetadataResponse regionResponse = client.get("/latest/meta-data/placement/region");
            String region = regionResponse.asString();

            Ec2MetadataResponse response = client.get("/latest/meta-data/placement/availability-zone");
            String availabilityZone = response.asString();
//            System.out.println("Region: " + region);
//            System.out.println("AZ: " + availabilityZone);
            return ResponseEntity.ok("Region: [" + region + "], AZ: [" + availabilityZone + "]");
        }
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok("hello!");
    }
}
