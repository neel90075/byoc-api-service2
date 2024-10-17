package com.learn.java.apihosting;

//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("1.0/posts")
public class MessagesRestController {
    private static final List<Country> COUNTRIES = List.of(
            new Country("United States of America", "Washington D.C.", 339_996_563),
            new Country("China", "Beijing", 1_411_750_000),
            new Country("India", "New Delhi", 1_428_627_663)
    );

    @GetMapping()
    public List<Country> getCountries(){
        return COUNTRIES;
    }

    @PostMapping("/{postId}/messages")
    @ResponseBody
    public String postAccessToken(@PathVariable String postId, @RequestBody JsonNode requestBody) {
        System.out.println("/messages Request Body" + requestBody);
        System.out.println("/messages Channel Id" + postId);

        String messageId = requestBody.path("replyToMessageIdOnExternalPlatform").asText();

        if(messageId.isBlank()){
            messageId = UUID.randomUUID().toString();
        }
        System.out.println("messageId" + messageId);
//        String endUserRecipientsId = requestBody.path("endUserRecipients").get(0).path("idOnExternalPlatform").asText();
//        String endUserRecipientsName = requestBody.path("endUserRecipients").get(0).path("name").asText();
//
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        String createdAt = currentDateTime.format(DateTimeFormatter.ISO_DATE_TIME); // ISO_OFFSET_DATE_TIME Format: YYYY-MM-DDTHH:mm:ss.SSS
//        JSONObject message = new JSONObject();
//
//        message.put("idOnExternalPlatform", "facebook-comment-id");
//        message.put("createdAtWithMilliseconds", createdAt);
//        message.put("url", "https://twitter.com/jack/status/20");
//
//        JSONObject thread = new JSONObject();
//        thread.put("idOnExternalPlatform", threadId);
//
//        JSONObject endUserIdentities = new JSONObject();
//        endUserIdentities.put("idOnExternalPlatform", endUserRecipientsId);
//        endUserIdentities.put("firstName", "Jack");
//        endUserIdentities.put("lastName", "Dorsey");
//        endUserIdentities.put("nickname", endUserRecipientsName);
//        endUserIdentities.put("image", "");
//        JSONArray endUserIdentitiesArray = new JSONArray();
//        endUserIdentitiesArray.put(endUserIdentities);
//
//        JSONObject recipients = new JSONObject();
//        recipients.put("idOnExternalPlatform", endUserRecipientsId);
//        recipients.put("name", endUserRecipientsName);
//        recipients.put("isPrimary", true);
//        recipients.put("isPrivate", true);
//        JSONArray recipientsArray = new JSONArray();
//        recipientsArray.put(recipients);
//
        JSONObject transformedJsonString = new JSONObject();
        transformedJsonString.put("idOnExternalPlatform", messageId);
//        transformedJsonString.put("thread", thread);
//        transformedJsonString.put("endUserIdentities", endUserIdentitiesArray);
//        transformedJsonString.put("recipients", recipientsArray);
//
        // Output the transformed JSON
        System.out.println("/messages Transformed JSON: " + transformedJsonString);

        return transformedJsonString.toString();
    }
}

