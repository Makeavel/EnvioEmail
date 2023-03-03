package com.api.envioemail.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    private String recipient; // vai virar um list
    private String msgBody;
    private String subject;
    private String attachment;
    
}
