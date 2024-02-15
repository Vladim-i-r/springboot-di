package com.vladimir.springboot.di.app.springbootdi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vladimir.springboot.di.app.springbootdi.models.Product;
import com.vladimir.springboot.di.app.springbootdi.services.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class SomeController {
    
    @Autowired                          //INYECCION DE INSTANCIA
    private ProductService service;        // Inyectando desde la interfaz                          el de abajo se inyecta mediante la clase
    //private ProductServiceImpl service; //= new ProductServiceImpl(); //esta instancia es la misma para todos los requests, por lo que se requiere aplicar INMUTABILIDAD

    @GetMapping
    public List<Product> list(){
        return service.findAll();
    } 

    @GetMapping("/{id}")
    public Product show(@PathVariable Long id) {
        return service.findById(id);
    }
    
}

