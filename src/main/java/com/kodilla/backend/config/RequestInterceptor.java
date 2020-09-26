package com.kodilla.backend.config;

import com.kodilla.backend.domain.Log;
import com.kodilla.backend.domain.repository.LogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
    @Autowired

    private LogDao logDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        long startTime = Instant.now().toEpochMilli();
        logger.info("Request URL::" + request.getRequestURL().toString() +
                ":: Start Time=" + Instant.now());
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        long startTime = (Long) request.getAttribute("startTime");

        logger.info("Request URL::" + request.getRequestURL().toString() +
                ":: Time Taken=" + (Instant.now().toEpochMilli() - startTime));
        logDao.save(new Log(request.getRequestURL().toString(), startTime, (Instant.now().toEpochMilli() - startTime)));
    }
}