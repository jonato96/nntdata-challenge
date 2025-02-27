package com.nntdata.client.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ClientDto {

    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String password;

}
