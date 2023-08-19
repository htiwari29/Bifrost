package com.bookit.bifrost.appservices;

import com.bookit.bifrost.common.exceptions.BifrostException;
import com.bookit.bifrost.domain.User;
import com.bookit.bifrost.entrypoints.dtos.RegisterRequest;
import com.bookit.bifrost.entrypoints.responses.BifrostResponse;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BifrostAuthService {

    private static final Logger log = LoggerFactory.getLogger(BifrostAuthService.class);

    private final UserRetrievalService userRetrievalService;

    public BifrostAuthService(UserRetrievalService userRetrievalService) {
        this.userRetrievalService = userRetrievalService;
    }

    public BifrostResponse register(RegisterRequest registerRequest) {
        try {
            userRetrievalService.userByUsernameAndTenantId(registerRequest.getUsername(), registerRequest.getTenantId());
        } catch (BifrostException ex) {
            // do something
        }
    }

}
