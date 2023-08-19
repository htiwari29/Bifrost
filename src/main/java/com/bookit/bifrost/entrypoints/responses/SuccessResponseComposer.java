package com.bookit.bifrost.entrypoints.responses;

import com.bookit.bifrost.entrypoints.responses.BifrostResponse;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;

public class SuccessResponseComposer {
    private final static Gson gson = new Gson();

    public static ResponseEntity<String> composeResponse(BifrostResponse response) {
        return ResponseEntity.ok(gson.toJson(response));
    }
}
