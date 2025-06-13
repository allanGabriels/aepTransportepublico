package com.controleonibus.aeptransportepublico.repository;

import com.controleonibus.aeptransportepublico.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
