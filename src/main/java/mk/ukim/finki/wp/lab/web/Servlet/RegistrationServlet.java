package mk.ukim.finki.wp.lab.web.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final RegistrationService _registrationService;

    public RegistrationServlet(SpringTemplateEngine springTemplateEngine, RegistrationService _registrationService) {
        this.springTemplateEngine = springTemplateEngine;
        this._registrationService = _registrationService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String workshopTitle = req.getParameter("workshopTitle");
        String participantName = req.getParameter("participantName");
        String participantEmail = req.getParameter("participantEmail");
        int numSeats = Integer.parseInt(req.getParameter("numSeats"));

        Registration registration;

        try{
            registration = _registrationService.register(workshopTitle,participantName,participantEmail,numSeats);
        }catch (IllegalArgumentException ex){
            String errorMessage = ex.getMessage();
            String encodedMessage = URLEncoder.encode(
                    errorMessage,
                    StandardCharsets.UTF_8
            );
            resp.sendRedirect("/?errorMessage="+encodedMessage);
            return;
        }

        context.setVariable("registration", registration);
        context.setVariable("ipAddress", req.getRemoteAddr());
        context.setVariable("userAgent", req.getHeader("User-Agent"));

        springTemplateEngine.process("registrationConfirmation.html",context,resp.getWriter());
    }
}
