package com.hifish.app.api.rest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class WechatIndex {
    private static Logger logger = Logger.getLogger(WechatIndex.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getContentType();
        request.getServletContext();
        System.out.println("do get");
        response.getWriter().write("OK");
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request) {
        System.out.println(request.getContentType());
        request.getContentType();
        request.getServletContext();
        System.out.println("do post");
    }
}
