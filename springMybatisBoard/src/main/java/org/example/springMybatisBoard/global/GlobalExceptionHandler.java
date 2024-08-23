package org.example.springMybatisBoard.global;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlertException.class)
    public String handleAlertException(AlertException ex, HttpServletRequest req) {
        req.setAttribute("msg", ex.getAlertType().getMessage());
        req.setAttribute("path", ex.getAlertType().getPath());
        return "alert";
    }
}
