package com.nntdata.client.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "client_id")
public class Client extends Person {

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Boolean status;

    public Client(Long id, String name, String gender, Integer age, String identification, String address, String phone, String password, Boolean status) {
        super(id, name, gender, age, identification, address, phone);
        this.password = password;
        this.status = status;
    }

}
