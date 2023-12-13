package com.codingapi.storage.server.pojo.response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {

    private String status;

    public void success() {
        this.status = "ok";
    }

}
