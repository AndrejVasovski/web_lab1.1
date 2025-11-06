package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.Model.Chef;
import mk.ukim.finki.wp.lab.Repository.DishRepository;
import mk.ukim.finki.wp.lab.Service.ChefService;
import mk.ukim.finki.wp.lab.Service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet (name = "DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final DishService dishService;
    private final ChefService chefService;



    public DishServlet(SpringTemplateEngine templateEngine, DishService dishService, ChefService chefService) {
        this.templateEngine = templateEngine;
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);


        String chefIdParm =  req.getParameter("chefId");
        if (chefIdParm == null) {
            resp.sendRedirect("/listChefs");
            return;
        }
        long chefId;
        try {
            chefId = Long.parseLong(chefIdParm);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid chefId");
            return;
        }

        Chef selectedChef = chefService.findById(chefId);
        if (selectedChef == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Chef not found");
            return;
        }

        WebContext webContext = new WebContext(webExchange);

        webContext.setVariable("chefName", selectedChef.getFirstName() + " " + selectedChef.getLastName());
        webContext.setVariable("dishes", dishService.listDishes());
        webContext.setVariable("chef", selectedChef);

        templateEngine.process("dishesList.html", webContext, resp.getWriter());


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chefId =  req.getParameter("chefId");
        resp.sendRedirect("/dish?chefId=" + chefId);
    }

}
