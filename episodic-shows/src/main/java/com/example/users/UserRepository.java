package com.example.users;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by trainer9 on 5/17/17.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
