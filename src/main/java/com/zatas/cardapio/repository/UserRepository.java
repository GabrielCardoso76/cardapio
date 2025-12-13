package com.zatas.cardapio.repository;

import com.zatas.cardapio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
