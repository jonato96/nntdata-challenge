package com.nntdata.transaction.client;

import com.nntdata.transaction.dto.client.ClientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-client", url = "http://localhost:8080/api/clients")
public interface CustomerClient {

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/{id}")
    public ClientResponseDto findById(@PathVariable("id") Long id);

}
