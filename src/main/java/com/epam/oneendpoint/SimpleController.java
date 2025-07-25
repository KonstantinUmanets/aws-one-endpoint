package com.epam.oneendpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.core.document.Document;
import software.amazon.awssdk.imds.Ec2MetadataClient;
import software.amazon.awssdk.imds.Ec2MetadataResponse;

import java.util.Map;

@RestController
public class SimpleController {

    @GetMapping("/metadata")
    public ResponseEntity<String> getMetadata() {
        try (Ec2MetadataClient client = Ec2MetadataClient.create()) {
            Ec2MetadataResponse response = client.get("/latest/meta-data/");
            Document document = response.asDocument();
            Map<String, Document> mapResults = document.asMap();
            return ResponseEntity.ok(response.asString());
        }
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok("hello!");
    }
}
