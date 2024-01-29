package com.example.is_site;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        e.printStackTrace();

        model.addAttribute("errorMessage", "Sorry, something went wrong.");

        return "error";
    }
}
