package com.learn.java.apihosting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("2.0/channel")
public class OutboundRestController {
    private static final List<Country> COUNTRIES = List.of(
            new Country("United States of America", "Washington D.C.", 339_996_563),
            new Country("China", "Beijing", 1_411_750_000),
            new Country("India", "New Delhi", 1_428_627_663)
    );

    @GetMapping()
    public List<Country> getCountries(){
        return COUNTRIES;
    }

//    @GetMapping("/token")
//    public Country getAccessToken(){
//        return new Country("United States of America", "Washington D.C.", 339_996_563);
//    }
    @PostMapping("/{channelId}/outbound")
    @ResponseBody
    public String postAccessToken(@PathVariable String channelId, @RequestBody JsonNode requestBody) throws JsonProcessingException {
        System.out.println("Request Body" + requestBody);
        System.out.println("Channel Id" + channelId);

        String threadId = requestBody.path("thread").path("idOnExternalPlatform").asText();
        String endUserRecipientsId = requestBody.path("endUserRecipients").get(0).path("idOnExternalPlatform").asText();
        String endUserRecipientsName = requestBody.path("endUserRecipients").get(0).path("name").asText();

//        ObjectMapper objectMapper = new ObjectMapper();
//        // Create a new JsonNode for the transformed request
//        ObjectNode transformedRequest = objectMapper.createObjectNode();


        LocalDateTime currentDateTime = LocalDateTime.now();
        String createdAt = currentDateTime.format(DateTimeFormatter.ISO_DATE_TIME); // ISO_OFFSET_DATE_TIME Format: YYYY-MM-DDTHH:mm:ss.SSS
        JSONObject message = new JSONObject();

        JSONObject transformedJsonString = new JSONObject();
        message.put("idOnExternalPlatform", "facebook-comment-id");
        message.put("createdAtWithMilliseconds", createdAt);
        message.put("url", "https://twitter.com/jack/status/20");

        JSONObject thread = new JSONObject();
        thread.put("idOnExternalPlatform", threadId);

        JSONObject endUserIdentities = new JSONObject();
        endUserIdentities.put("idOnExternalPlatform", endUserRecipientsId);
        endUserIdentities.put("firstName", "Jack");
        endUserIdentities.put("lastName", "Dorsey");
        endUserIdentities.put("nickname", endUserRecipientsName);
        endUserIdentities.put("image", "");
        JSONArray endUserIdentitiesArray = new JSONArray();
        endUserIdentitiesArray.put(endUserIdentities);

        JSONObject recipients = new JSONObject();
        recipients.put("idOnExternalPlatform", endUserRecipientsId);
        recipients.put("name", endUserRecipientsName);
        recipients.put("isPrimary", true);
        recipients.put("isPrivate", true);
        JSONArray recipientsArray = new JSONArray();
        recipientsArray.put(recipients);

        transformedJsonString.put("message", message);
        transformedJsonString.put("thread", thread);
        transformedJsonString.put("endUserIdentities", endUserIdentitiesArray);
        transformedJsonString.put("recipients", recipientsArray);

//        transformedRequest.put("message", message);
//        transformedRequest.put("tenantId", tenantId);
//        transformedRequest.put("businessUnitId", businessUnitId);
//        transformedRequest.put("amount", amount);
//
//        // Convert the transformed JsonNode to a JSON string
//        String transformedJsonString = objectMapper.writeValueAsString(transformedRequest);

        // Output the transformed JSON
        System.out.println("Transformed JSON: " + transformedJsonString);



        return transformedJsonString.toString();
    }

    @GetMapping("/token/{id}")
    public Country getAccessTokenById(@PathVariable int id){
        return new Country("United States of America", "Washington D.C.", id);
    }
}

