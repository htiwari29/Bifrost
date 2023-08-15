package com.bookit.bifrost.appservices;

import com.bookit.bifrost.common.errors.ErrorFactory;
import com.bookit.bifrost.common.exceptions.BifrostException;
import com.bookit.bifrost.common.util.TenantUserToken;
import com.bookit.bifrost.domain.User;
import com.bookit.bifrost.domain.repositories.UserRepository;
import com.bookit.bifrost.entrypoints.dtos.UserDetail;
import org.jvnet.hk2.annotations.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userByUsername(username);
        return UserDetail.build(user);
    }

    @Transactional
    public UserDetails loadUserDetails(Authentication authentication){
        String tenantId = ((TenantUserToken) authentication).getTenantId();
        String username = authentication.getName();
        User user = userByUsernameAndTenantId(username, tenantId);
        return UserDetail.build(user);
    }

    public User userByUsername(String username) {
        User user;
        try {
            user = userRepository.userByUsername(username);
        } catch (Exception ex) {
            throw new BifrostException(ex, ErrorFactory.createGenericError());
        }
        if (Objects.isNull(user))
            throw new BifrostException(ErrorFactory.userNotFoundWhileRetrievingUserByUsername());
        return user;
    }

    public User userByUsernameAndTenantId(String username, String tenantId){
        User user;
        try {
            user = userRepository.userByUsernameAndTenantId(username, tenantId);
        } catch (Exception ex) {
            throw  new BifrostException(ex, ErrorFactory.createGenericError());
        }
        if (Objects.isNull(user))
            throw new BifrostException(ErrorFactory.userNotFoundWhileRetrievingUserByUsernameAndTenantId());
        return user;
    }

}
