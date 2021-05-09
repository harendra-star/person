package com.iag.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenderizeResponse {
    private String name;
    private String gender;
    private Double probability;
    private Integer count;
}
