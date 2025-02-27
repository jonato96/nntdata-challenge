package com.nntdata.transaction.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ClientResponseDto implements Serializable {

    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private boolean status;

}
