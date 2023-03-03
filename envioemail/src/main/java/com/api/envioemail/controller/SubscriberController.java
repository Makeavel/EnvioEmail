package com.api.envioemail.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.envioemail.DTO.SubscribersDTO;
import com.api.envioemail.model.Subscriber;
import com.api.envioemail.service.EmailService;
import com.api.envioemail.service.SubscribersService;
import com.api.envioemail.utils.ValidateEmailUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/subscribers")
@RequiredArgsConstructor
public class SubscriberController {
    
    private final SubscribersService subscribersService;
    private final EmailService emailService;
    private final ModelMapper modelMapper;


    @GetMapping("/")
    public List<SubscribersDTO> readAll() throws Exception{
        return subscribersService.readAll().stream()
                .map(subscribers -> modelMapper.map(subscribers, SubscribersDTO.class))
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<SubscribersDTO> readById(@PathVariable("id") UUID id) throws Exception{
        Subscriber subs = subscribersService.readById(id);
        SubscribersDTO subDTO = modelMapper.map(subs, SubscribersDTO.class);

        return ResponseEntity.ok().body(subDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<SubscribersDTO> create(@RequestBody SubscribersDTO subscribersDTO) throws Exception{
        boolean validate = ValidateEmailUtils.isValidEmailAddressRegex(subscribersDTO.getEmail());
        if( validate ) {
            Subscriber sub = modelMapper.map(subscribersDTO, Subscriber.class);
            Subscriber subDTO = subscribersService.create(sub);
            SubscribersDTO subResponse = modelMapper.map(subDTO, SubscribersDTO.class);
            emailService.sendMail(subscribersDTO.getEmail());
            return new ResponseEntity<SubscribersDTO>(subResponse, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<SubscribersDTO>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
