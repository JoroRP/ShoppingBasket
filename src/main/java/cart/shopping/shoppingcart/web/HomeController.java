package cart.shopping.shoppingcart.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    public HomeController() {
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView, HttpSession httpSession){
        modelAndView.setViewName("index");
        modelAndView.addObject("name",httpSession.getAttribute("application-name"));
        return modelAndView;
    }
}
