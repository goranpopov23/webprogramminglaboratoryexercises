package mk.ukim.finki.wp.lab.web.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "WorkshopServlet", urlPatterns = "")
public class WorkshopListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final WorkshopService _workshopService;

    public WorkshopListServlet(SpringTemplateEngine ste, WorkshopService ws){
        springTemplateEngine = ste;
        _workshopService = ws;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        List<Workshop> workshopList;
        Set<String> categories = _workshopService.listAll().stream().map(Workshop::getCategory).collect(Collectors.toSet());

        String workshopSearchBarText = req.getParameter("text");
        String workshopSearchBarMaxPrice = req.getParameter("maxPrice");
        int maxPrice = 0;

        if(workshopSearchBarMaxPrice != null && !workshopSearchBarMaxPrice.isEmpty()){
            try{
                maxPrice = Integer.parseInt(workshopSearchBarMaxPrice);
            }catch (NumberFormatException ex){
                maxPrice = 0;
            }
        }

        if((workshopSearchBarMaxPrice == null || workshopSearchBarText.isEmpty()) && maxPrice == 0){
            workshopList = _workshopService.listAll();
        }else{
            workshopList = _workshopService.search(workshopSearchBarText, (double) maxPrice);
        }

        context.setVariable("workshops", workshopList);
        context.setVariable("categories", categories);

        springTemplateEngine.process("listWorkshops.html", context, resp.getWriter());
    }
}
