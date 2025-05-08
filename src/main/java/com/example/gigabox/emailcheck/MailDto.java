package com.example.gigabox.emailcheck;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailDto {
    private String from;
    private String to;
    private String title;
    private String message;
}
