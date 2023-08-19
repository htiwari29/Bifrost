package com.bookit.bifrost.appservices;

import com.bookit.bifrost.common.util.TenantUserToken;
import com.bookit.bifrost.domain.User;
import com.bookit.bifrost.entrypoints.dtos.UserDetail;
import org.jvnet.hk2.annotations.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRetrievalService userRetrievalService;

    public UserDetailService(UserRetrievalService userRetrievalService) {
        this.userRetrievalService = userRetrievalService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRetrievalService.userByUsername(username);
        return UserDetail.build(user);
    }

    @Transactional
    public UserDetails loadUserDetails(Authentication authentication){
        String tenantId = ((TenantUserToken) authentication).getTenantId();
        String username = authentication.getName();
        User user = userRetrievalService.userByUsernameAndTenantId(username, tenantId);
        return UserDetail.build(user);
    }

}
