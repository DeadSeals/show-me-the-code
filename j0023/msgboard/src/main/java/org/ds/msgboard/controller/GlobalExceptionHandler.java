package org.ds.msgboard.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.ds.msgboard.dto.MsgResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MsgResult defaultExceptionHandler(Exception e) {
        return new MsgResult(MsgResult.FAIL_CODE,e.getMessage());
    }

}
