package com.xbo.studyspring.config;

import com.xbo.studyspring.exception.CustomAsyncRequestTimeoutException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

public class TimeoutCallableProcessor extends CallableProcessingInterceptorAdapter {
    @Override
    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
        HttpServletRequest httpRequest = request.getNativeRequest(HttpServletRequest.class);
        //记录超时的url地址
        return new CustomAsyncRequestTimeoutException(httpRequest.getRequestURI());
    }
}