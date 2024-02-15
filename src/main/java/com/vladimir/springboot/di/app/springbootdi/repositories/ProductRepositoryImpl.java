package com.vladimir.springboot.di.app.springbootdi.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

import com.vladimir.springboot.di.app.springbootdi.models.Product;

@RequestScope
@Repository("productList")
public class ProductRepositoryImpl implements ProductRepository {
    
    private List<Product> data;

    public ProductRepositoryImpl() {
        this.data = Arrays.asList(
            new Product(1L, "Memoria Corsair 32", 300L),
            new Product(2L, "CPU Inter Core 9", 850L),
            new Product(3L, "Teclado Razer Mini", 180L), 
            new Product(4L, "Motherboard Gigabyte", 490L) 
            
        );
    }

    @Override  //Con esta anotacion identificamos como programador que es una implementacion del metodo de la interfaz
    public List<Product> findAll(){
        return data;
    }

    @Override
    public Product findById(Long id){
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    
}
