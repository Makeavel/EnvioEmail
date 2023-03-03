package com.api.envioemail.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.envioemail.model.Subscriber;
import com.api.envioemail.repository.SubscribersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscribersServiceImpl implements SubscribersService {

    private final SubscribersRepository repository;

    @Override
    public List<Subscriber> readAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Subscriber readById(UUID id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SUBSCRIBERS NOT FOUND"));
    }

    @Override
    public Subscriber create(Subscriber subscribers) throws Exception {
        return repository.save(subscribers);
    }
    
}
