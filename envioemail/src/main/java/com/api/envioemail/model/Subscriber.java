package com.api.envioemail.model;

import java.util.UUID;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {
    
    private UUID id;

    private String email;

    private String name;

}
