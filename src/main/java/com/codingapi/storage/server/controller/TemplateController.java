package com.codingapi.storage.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.storage.server.pojo.StudyTemplate;
import com.codingapi.storage.server.pojo.response.Response;
import com.codingapi.storage.server.pojo.response.StudyTemplateResponse.ListResponse;
import com.codingapi.storage.server.pojo.response.StudyTemplateResponse.DataResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/storage/1.1/")
@CrossOrigin
public class TemplateController {

    private final Map<String, StudyTemplate> cache = new HashMap<>();

    @PostMapping("/study_templates")
    public Response saveTemplate(HttpServletRequest request, StudyTemplate data) {
        String client = request.getParameter("client");
        String user = request.getParameter("user");

        log.info("save study_templates:{}", data.getName());
        cache.put(data.getName(), data);

        Response response = new Response();
        response.success();

        return response;
    }


    @GetMapping("/study_templates")
    public Response getTemplates(HttpServletRequest request) {
        String client = request.getParameter("client");
        String user = request.getParameter("user");
        String template = request.getParameter("template");

        if(StringUtils.hasText(template)){
            log.info("get study_template");
            StudyTemplate studyTemplate = cache.get(template);

            DataResponse response = new DataResponse();
            response.success();
            response.setData(studyTemplate);
            return response;
        }else{
            log.info("search study_templates");
            ListResponse response = new ListResponse();
            response.success();
            response.push(new ArrayList<>(cache.keySet()));
            return response;
        }
    }

    @DeleteMapping("/study_templates")
    public Response deleteTemplate(HttpServletRequest request) {
        String client = request.getParameter("client");
        String user = request.getParameter("user");
        String template = request.getParameter("template");

        log.info("delete study_templates:{}", template);
        cache.remove(template);

        Response response = new Response();
        response.success();

        return response;
    }

}
