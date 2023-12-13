package com.codingapi.storage.server.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Chart {
    

    private String id;
    private String name;
    private String content;
    private String user;
    private String client;

    private String symbol;
    private String resolution;

    private long timestamp;

  

    public void init(){
        this.timestamp = System.currentTimeMillis() / 1000;
        this.id = this.name;
    }



    public void copy(Chart data) {
        this.name = data.name;
        this.content = data.content;
        this.user = data.user;
        this.client = data.client;
        this.symbol = data.symbol;
        this.resolution = data.resolution;
    }

}
