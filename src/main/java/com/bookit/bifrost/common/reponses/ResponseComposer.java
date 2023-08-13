package com.bookit.bifrost.common.reponses;

import com.bookit.bifrost.common.exceptions.InvalidDataException;
import com.bookit.bifrost.common.exceptions.BifrostCreationException;
import com.bookit.bifrost.common.exceptions.BifrostException;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseComposer {

    private static final Gson gson = new Gson();

    protected ResponseComposer() { }

    public static <T> ResponseEntity<String> composeResponse(T response){
        return (response instanceof BifrostException ex) ? failureResponse(ex) : successResponse(response);
    }

    private static <T> ResponseEntity<String> successResponse(T response){
        return ResponseEntity.ok(gson.toJson(response));
    }

    private static ResponseEntity<String> failureResponse(BifrostException ex) {
        ResponseEntity.BodyBuilder responseEntity = assignStatusCode(ex);
        return responseEntity.body(gson.toJson(ex.getErrors()));
    }

    private static ResponseEntity.BodyBuilder assignStatusCode(BifrostException ex) {
        if (ex instanceof BifrostCreationException || ex instanceof InvalidDataException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
