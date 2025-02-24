package com.nntdata.client.mapper;

import com.nntdata.client.dto.ClientDto;
import com.nntdata.client.dto.ClientResponseDto;
import com.nntdata.client.entity.Client;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientMapper {
    public ClientResponseDto toClientDto(Client client){
        return ClientResponseDto.builder()
                .id(client.getId())
                .name(client.getName())
                .gender(client.getGender())
                .age(client.getAge())
                .identification(client.getIdentification())
                .address(client.getAddress())
                .phone(client.getPhone())
                .status(client.isStatus())
                .build();
    }

    public List<ClientResponseDto> toClientDtoList(List<Client> clients) {
        return clients.stream().map(this::toClientDto).toList();
    }

    public Client toClient(ClientDto clientDto){
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setGender(clientDto.getGender());
        client.setAge(clientDto.getAge());
        client.setIdentification(clientDto.getIdentification());
        client.setAddress(clientDto.getAddress());
        client.setPassword(clientDto.getPassword());
        client.setPhone(clientDto.getPhone());
        return client;
    }

}
