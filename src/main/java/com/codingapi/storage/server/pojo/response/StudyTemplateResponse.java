package com.codingapi.storage.server.pojo.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class StudyTemplateResponse {
    
    @Setter
    @Getter
    public static class ListResponse extends Response{

        private List<Data> data;

        public ListResponse() {
            this.data = new ArrayList<>();
        }

        public void push(List<String> names) {
            for (String name : names) {
                Data data = new Data();
                data.setName(name);
                this.data.add(data);
            }
        }
    
        
    }


    @Setter
    @Getter
    public static class Data {

        private String name;
    
        
    }

}
