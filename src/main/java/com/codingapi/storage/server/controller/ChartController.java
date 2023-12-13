package com.codingapi.storage.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.storage.server.pojo.Chart;
import com.codingapi.storage.server.pojo.response.Response;
import com.codingapi.storage.server.pojo.response.ChartResponse.DataResponse;
import com.codingapi.storage.server.pojo.response.ChartResponse.ListResponse;
import com.codingapi.storage.server.pojo.response.ChartResponse.SaveResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/storage/1.1/")
@CrossOrigin
public class ChartController {

    private final Map<String, Chart> cache = new HashMap<>();

    @GetMapping("/charts")
    public Response loadCharts(HttpServletRequest request) {
        String client = request.getParameter("client");
        String user = request.getParameter("user");
        String chart = request.getParameter("chart");

        if (StringUtils.hasText(chart)) {
            log.info("load client:{},user:{},chart:{}", client, user, chart);
            Chart c = cache.get(chart);
            DataResponse response = new DataResponse();
            response.success();
            response.setData(c);
            return response;
        } else {
            log.info("list client:{},user:{},chart:{}", client, user, chart);
            ListResponse response = new ListResponse();
            response.success();
            response.setData(cache.values());
            return response;
        }

    }

    @PostMapping("/charts")
    @ResponseBody
    public Response saveCharts(Chart data, HttpServletRequest request) {
        String chart = request.getParameter("chart");

        if (StringUtils.hasText(chart)) {
            log.info("save as chart:{} to :{}", chart, data.getName());
            Chart current = cache.get(chart);
            current.copy(data);
            current.init();

            cache.remove(chart);
            cache.put(current.getName(), current);

            Response response = new Response();
            response.success();
            return response;

        } else {
            log.info("save chart:{}", data.getName());
            data.init();
            cache.put(data.getName(), data);

            SaveResponse response = new SaveResponse();
            response.success();
            response.setId(data.getName());
            return response;
        }

    }

    @DeleteMapping("/charts")
    public Response deleteCharts(HttpServletRequest request) {
        String client = request.getParameter("client");
        String user = request.getParameter("user");
        String chart = request.getParameter("chart");

        log.info("delete client:{},user:{},chart:{}", client, user, chart);
        cache.remove(chart);

        Response response = new Response();
        response.success();
        return response;
    }

}
