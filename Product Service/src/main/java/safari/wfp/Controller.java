package safari.wfp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/products")
public class Controller {
    @GetMapping(path = "/get")
    public String welcome(){
        return "Hello product service";
    }

}
