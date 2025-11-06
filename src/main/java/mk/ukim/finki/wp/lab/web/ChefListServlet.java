package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletContext;
import org.thymeleaf.context.WebContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.Model.Chef;
import mk.ukim.finki.wp.lab.Service.ChefService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;


import java.io.IOException;
import java.util.List;
@WebServlet(name = "ChefListSevlet", urlPatterns = "/listChefs")
@Component
public class ChefListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final ChefService chefService;

    public ChefListServlet(SpringTemplateEngine templateEngine, ChefService chefService) {
        this.templateEngine = templateEngine;
        this.chefService = chefService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req,resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("chefs", chefService.listChefs());

        templateEngine.process("listChefs", context, resp.getWriter());
    }


}
