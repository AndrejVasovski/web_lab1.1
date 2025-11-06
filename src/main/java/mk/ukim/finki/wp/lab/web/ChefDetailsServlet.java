package mk.ukim.finki.wp.lab.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wp.lab.Model.Chef;
import mk.ukim.finki.wp.lab.Model.Dish;
import mk.ukim.finki.wp.lab.Service.ChefService;
import mk.ukim.finki.wp.lab.Service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@Slf4j
@WebServlet (name="ChefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final ChefService chefService;
    private final DishService dishService;

    public ChefDetailsServlet(SpringTemplateEngine templateEngine, ChefService chefService, DishService dishService) {
        this.templateEngine = templateEngine;
        this.chefService = chefService;
        this.dishService = dishService;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long chefId = Long.parseLong(req.getParameter("chefId"));
        String dishId = req.getParameter("dishId");

        Chef chef = chefService.findById(chefId);
        Dish dish = dishService.findByDishId(dishId);

        if (dish==null || chef==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid chef id or dish id");
            return;

        }
        chefService.addDishToChef(chefId,dishId);


        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req,resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("chef",chef);
        context.setVariable("dish",chef.getDishes());

        templateEngine.process("chefDetails",context,resp.getWriter());

    }



}
