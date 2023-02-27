package com.muchyla.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muchyla.ecommerce.models.TwoFactorCode;
import com.muchyla.ecommerce.models.User;

public interface TwoFactorCodeRepository extends JpaRepository<TwoFactorCode, Long> {

	Optional<TwoFactorCode> findByOwner(User user);
}
