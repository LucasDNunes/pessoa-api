package br.com.pessoa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
public class SourceController {

    @GetMapping
    public String github() {
        return "back: https://github.com/LucasDNunes/pessoa-api \n <br>" +
                "front: https://github.com/LucasDNunes/pessoa-front";
    }
}
