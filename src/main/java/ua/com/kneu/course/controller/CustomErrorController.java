package ua.com.kneu.course.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute("jakarta.servlet.error.status_code");

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "redirect:/error/404";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "redirect:/error/403";
            }
        }


        return "error404";
    }

    @GetMapping("/error/403")
    public String error403(Model model) {
        return "error403";
    }

    @GetMapping("/error/404")
    public String error404(Model model) {
        return "error404";
    }

    @GetMapping("/403")
    public String PageError403() {
        return "error403";
    }

}