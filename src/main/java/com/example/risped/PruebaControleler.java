package com.example.risped;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class PruebaControleler {

    // Obtener todos los registros de Entidad
    @GetMapping("/all")
    public String getAllDocument() {
        return "Pruebaaa";
    }

}
