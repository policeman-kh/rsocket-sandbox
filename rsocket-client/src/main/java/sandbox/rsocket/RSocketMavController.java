package sandbox.rsocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class RSocketMavController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
