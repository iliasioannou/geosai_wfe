package it.planetek.cmems_activiti_loader;

import org.springframework.core.io.FileSystemResource;

import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;


/**
 * Created by Francesco Bruni <bruni@planetek.it> on 6/15/17.
 */

public class Loader {

    private static final String BASEPATH = "/src/water_quality/src/main/resources/diagrams/";
    private static final String BASEURL =  "http://localhost:8080/activiti-rest/service/repository/deployments";

    private static boolean checkForExistingFlow(String response, String flow){
        return response.contains(flow);
    }

    public static void main(String[] args) throws IllegalStateException, IOException {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setMessageConverters(Arrays.asList(
                new FormHttpMessageConverter(),
                new StringHttpMessageConverter()
        ));
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("kermit", "kermit"));

        String allFlows = restTemplate.getForObject(BASEURL, String.class);
        for (String flow: Arrays.asList("WaterQuality_CMEMS.bpmn", "WaterQuality_CMEMS10.bpmn", "WaterQuality_CMEMS30.bpmn")) {
            //if (!checkForExistingFlow(allFlows, flow)){
                MultiValueMap<String, Object> form
                        = new LinkedMultiValueMap<String, Object>();

                form.add("deployment", new FileSystemResource(BASEPATH + flow));
                restTemplate.postForLocation(BASEURL, form, String.class);
            //}
        }
    }






}

