package com.longfor.gais.gfs.apollo;

import com.google.common.io.CharStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author zhendong.li@outlook.com
 * @version 2018/8/16
 */

@RestController
@RequestMapping("/v1/echo")
public class EchoController {
    private String hostName;
    private String ip;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${timeout:100}")
    private int timeout;

    @PostConstruct
    private void init()
    {
        try
        {
            this.hostName = InetAddress.getLocalHost().getHostName();
            this.ip = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }


    @Autowired
    private Environment environment;


    @RequestMapping(value={"/**"})
    public Object welcome(HttpServletRequest request) throws IOException {
        String mvcPath = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        Map<String, String[]> parameters = request.getParameterMap();

        String body = CharStreams.toString(request.getReader());
        HashMap<String, Object> map = new LinkedHashMap();
        map.put("URI", mvcPath);
        map.put("method", request.getMethod());
        map.put("parameter", parameters);
        map.put("body", body);
        map.put("ip", this.ip);
        map.put("hostname", this.hostName);
        map.put("remoteAddress", request.getRemoteAddr());
        map.put("remotePort", Integer.valueOf(request.getRemotePort()));
        map.put("time", new Date().toString());


        map.put("server.port", environment.getProperty("server.port"));
        map.put("timeout", timeout);
        map.put("username", username);
        map.put("password", password);

        initializeEndpoints();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        sleepRandomTime(10, 200);
        return map;
    }

    private void sleepRandomTime(int min, int max){
        long randomNum = min + (long)(Math.random() * ((max - min) + 1));
        try {
            Thread.sleep(randomNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<String> initializeEndpoints() {
        String key = String.format("analytics.elasticsearch.endpoints[%s]", 0);
        List<String> endpoints = new ArrayList();

        while (environment.containsProperty(key)) {
            String url = environment.getProperty(key);
            endpoints.add(url);

            key = String.format("analytics.elasticsearch.endpoints[%s]", endpoints.size());
        }


        return endpoints;
    }
}
