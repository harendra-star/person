package com.iag.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgifyResponse {
    private String name;
    private Integer age;
    private Integer count;
}
