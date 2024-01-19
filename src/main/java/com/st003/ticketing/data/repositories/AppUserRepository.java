package com.st003.ticketing.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.st003.ticketing.data.entities.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
}
