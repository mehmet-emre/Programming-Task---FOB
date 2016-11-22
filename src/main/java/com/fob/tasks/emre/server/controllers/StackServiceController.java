package com.fob.tasks.emre.server.controllers;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fob.tasks.emre.common.InjectionManager;
import com.fob.tasks.emre.common.PropertyManager;
import com.fob.tasks.emre.server.exception.ElementNotFoundException;
//import com.fob.tasks.emre.server.exception.UnauthorizedRequestException;
import com.fob.tasks.emre.services.StackServiceBasicImpl;

@RestController
@RequestMapping("/stackservice")
public class StackServiceController {

    private static Logger logger = LogManager.getLogger(StackServiceController.class);

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", PropertyManager.REST_URI);
        response.setHeader("Access-Control-Allow-Methods",
                "POST,GET,OPTIONS,DELETE");
        response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader(
                "Access-Control-Allow-Headers",
                "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
    }

    @ModelAttribute
    public void validateRequest(HttpServletRequest request) {
//        if (request.getHeader("authorization") == null ||  !request.getHeader("authorization").equals(PropertyManager.ACCESS_KEY)) {
//            throw new UnauthorizedRequestException();
//        }
    }

    @RequestMapping("/push")
    public void push(@RequestParam(value="item", defaultValue="X") int item, HttpServletResponse  response) {
        InjectionManager.getInstance(StackServiceBasicImpl.class).push(item);
    }

    @RequestMapping("/pop")
    public int stack(HttpServletResponse  response) {
        try {
            return InjectionManager.getInstance(StackServiceBasicImpl.class).pop(); 
        } catch (NoSuchElementException e) {
            logger.info("Stack is empty!");
            throw new ElementNotFoundException("Stack is emtpy!");
        }
    }

    @RequestMapping("/view")
    public int[] view(HttpServletResponse  response) {
        return InjectionManager.getInstance(StackServiceBasicImpl.class).view();
    }
}