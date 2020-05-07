package org.moy.spring.http;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/indexGet")
    public String indexGet() {
        return "indexGet";
    }

    @PostMapping("/indexPost")
    public String indexPost() {
        return "indexPost";
    }

    @RequestMapping("/index")
    public String index(Demo demo) {
        return "index:" + demo;
    }

    @RequestMapping("/indexStr")
    public String indexBody(@RequestBody String demo) {
        return "index:" + demo;
    }

    @RequestMapping("/indexBody")
    public String indexBody(@RequestBody Demo demo) {
        return "indexBody:" + demo;
    }
}
