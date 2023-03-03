package com.api.envioemail.DTO;

import com.api.envioemail.model.Subscriber;

import lombok.Data;

@Data
public class SubscribersDTO {
    
    private String email;

    private String name;

    public SubscribersDTO subscribers (Subscriber subs){
        var subscriber = new SubscribersDTO();

        subscriber.setEmail(subs.getEmail());
        subscriber.setName(subs.getName());

        return subscriber;
    }
}
