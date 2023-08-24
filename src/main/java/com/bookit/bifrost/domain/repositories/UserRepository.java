package com.bookit.bifrost.domain.repositories;

import com.bookit.bifrost.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String username);

	User findByUsernameAndTenantId(String username, String tenantId);

}
