package com.api.envioemail.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.envioemail.model.Subscriber;

public interface SubscribersRepository extends JpaRepository<Subscriber, UUID>{
    
}
