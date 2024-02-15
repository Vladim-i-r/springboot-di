package com.vladimir.springboot.di.app.springbootdi.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladimir.springboot.di.app.springbootdi.models.Product;

public class ProductRepositoryJson implements ProductRepository{

    private List<Product> list;

    public ProductRepositoryJson(){  //Primer metodo, sin inyeccion 
        Resource resource = new ClassPathResource("json/product.json");  //Leer un archivo
        readValueJson(resource);
    }

    public ProductRepositoryJson(Resource resource){ // Segundo metodo, creando un Value e inyectarlo desde AppConfig
        readValueJson(resource);
    }

    private void readValueJson(Resource resource){  // Como ambos llevan la misma logica, se crea un este metodo
        ObjectMapper objectMapper = new ObjectMapper();  //Permite convertir un archivo o file, a un objeto de java. 
        try {
            list = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class));  //Lo convertimos a un arreglo de objeto y de ahi al tipo Product
        } catch (IOException e) {                             //.getInputStream()  tambien funciona 
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }

}
