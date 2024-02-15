package com.vladimir.springboot.di.app.springbootdi.repositories;

import java.util.List;

import com.vladimir.springboot.di.app.springbootdi.models.Product;

public interface ProductRepository {

    List<Product> findAll(); //Ya no se coloca el public porque ya la misma interfaz es publica
    
    Product findById(Long id);
}
