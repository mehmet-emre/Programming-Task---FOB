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
import com.fob.tasks.emre.server.exception.UnauthorizedRequestException;
import com.fob.tasks.emre.services.StackServiceBasicImpl;

/**
 * Rest controller for stack service
 * @author Emre
 */
@RestController
@RequestMapping("/stackservice")
public class StackServiceController {

    private static Logger logger = LogManager.getLogger(StackServiceController.class);

    /**
     * Set headers
     * @param response
     */
    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
        response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
    }

    /**
     * Check 'authorization' header parameter
     * @param request
     */
    @ModelAttribute
    public void validateRequest(HttpServletRequest request) {
        if (request.getHeader("authorization") == null ||  !request.getHeader("authorization").equals(PropertyManager.getAuthKey())) {
            throw new UnauthorizedRequestException();
        }
    }

    @RequestMapping("/push")
    public void push(@RequestParam(value="item") int item, HttpServletResponse  response) {
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