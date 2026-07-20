package com.schedule.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<com.schedule.user.entity.User, Long> {
}
