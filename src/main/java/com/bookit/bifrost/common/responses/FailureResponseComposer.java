package com.bookit.bifrost.common.responses;

import com.bookit.bifrost.common.exceptions.BifrostException;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FailureResponseComposer {

	private FailureResponseComposer() {
	}

	private static final Gson gson = new Gson();

	public static ResponseEntity<String> composeResponse(BifrostException response) {
		ResponseEntity.BodyBuilder responseEntity = assignStatusCode(response);
		return responseEntity.body(gson.toJson(response.getErrors()));
	}

	private static ResponseEntity.BodyBuilder assignStatusCode(BifrostException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
