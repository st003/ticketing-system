package com.st003.ticketing.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.st003.ticketing.data.enties.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {}
