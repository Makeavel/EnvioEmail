package com.api.envioemail.service;


import java.util.List;
import java.util.UUID;

import com.api.envioemail.model.Subscriber;

public interface SubscribersService {

    List<Subscriber> readAll() throws Exception;

    Subscriber readById(UUID id) throws Exception;

    Subscriber create(Subscriber subscribers) throws Exception;
    
}
