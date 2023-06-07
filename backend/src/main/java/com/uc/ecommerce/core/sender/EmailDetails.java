package com.uc.ecommerce.core.sender;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmailDetails {
   private String from;
    private String to;
    private String subject;
    private String text;
}
