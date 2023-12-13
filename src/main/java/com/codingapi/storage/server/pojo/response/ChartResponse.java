package com.codingapi.storage.server.pojo.response;

import java.util.Collection;

import com.codingapi.storage.server.pojo.Chart;

import lombok.Getter;
import lombok.Setter;

public class ChartResponse {

    @Setter
    @Getter
    public static class SaveResponse extends Response {

        private String id;

    }

    @Setter
    @Getter
    public static class DataResponse extends Response {

        private Chart data;

    }

    @Setter
    @Getter
    public static class ListResponse extends Response {

        private Collection<Chart> data;

    }

}
