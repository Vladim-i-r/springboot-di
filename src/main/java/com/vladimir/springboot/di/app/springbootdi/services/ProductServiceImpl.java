package com.vladimir.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.vladimir.springboot.di.app.springbootdi.models.Product;                  
import com.vladimir.springboot.di.app.springbootdi.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private Environment environment;

    // @Value("${config.price.tax}")
    // private double tax;
    //*********************INYECCION DE DEPENDENCIAS*********************** */
                            /*Mediante la interfaz */

    //@Autowired                              // No nos llames, nosotros te llamamos/proveemos
    // @Qualifier("productList")                Asi seria la seleccion de implementacion cuando no es constructor
    private ProductRepository repo;         // inyectando desde la interfaz

                            /*Mediante la clase */
                    
    //private ProductRepositoryImpl repo = new ProductRepositoryImpl(); Con AUTOWIRED ya no es necesario instanciar, ya que como es componente si instancia solo

                            /*Mediante el SETTER */
    // @Autowired                              //Inyectando mediante el metodo SET
    // public void setRepo(ProductRepository repo) {
    //     this.repo = repo;
    // }

                            /*Mediante el CONSTRUCTOR */
                            //@Qualifier("productList") en el argumento
    public ProductServiceImpl(@Qualifier("productJson") ProductRepository repo) {  //Con  este no se necesita el @Autowired   @Qualifier indica que repository se va a implementar
        this.repo = repo;     //Si se elimina, agarra el @Primary                                                              donde el ProductRepositoryFoo es el principal y con esto, seleccionamos el Impl
    } 
        

    @Override   // Sin @RequestScope, solucion inmutable
    public List<Product> findAll() {
        return repo.findAll().stream().map(p -> {   // el map devuelve un stream
            Double priceTax = p.getPrice() * environment.getProperty("config.price.tax", double.class);  //*tax  */ al hacer la operacion se convierte en double decimal
            // Product newProduct = new Product(p.getId(), p.getName(), priceTax.longValue()); // por lo que hay que regresarla a tipo de variable Long
            Product newProduct = (Product) p.clone();  // Manera elegante de inmutabilidad, con CLONE, clone regresa tipo object, pero se implementa cast a Product
            newProduct.setPrice(priceTax.longValue()); 
            return newProduct;                      //con esto se aplica el principio de INMUTABILIDAD, creando un nuevo objeto para que no se modifique en cada request
        }).collect(Collectors.toList());          // con este lo convertimos para devolver una lista 
    }

    // @Override     //Con @Requestscope, pero no es inmutable, es decir, se puede ver afectado por cualquier usuario 
    // public List<Product> findAll() {
    //     return repo.findAll().stream().map(p -> {   
    //         Double priceTax = p.getPrice() * 1.25d;    
    //         p.setPrice(priceTax.longValue());  // Al hacer esto, en cada request que se haga, se incrementara el priceTax, pero se puede solucionar colocando
    //         return p;                          // @RequestScope en el ProductRepositoryImpl, lo que hara que por cada request se cree un objeto y no se almacene en memoria 
    //     }).collect(Collectors.toList());       // Donde normalmente se crea un objeto para toda la aplicacion, por ello cambia con cada request 
    // }

    @Override
    public Product findById(Long id) {
        return repo.findById(id);
    }


}
 