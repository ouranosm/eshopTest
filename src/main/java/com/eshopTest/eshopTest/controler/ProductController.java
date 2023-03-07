package com.eshopTest.eshopTest.controler;

import com.eshopTest.eshopTest.entity.Product;
import com.eshopTest.eshopTest.entity.ProductUpdate;
import com.eshopTest.eshopTest.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public class ProductController {
    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/products/{id}")
    public Product one(@PathVariable Long orderId) {
        return repo.findById(orderId)
                .orElseThrow();
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public  Product addProduct(@RequestBody Product product){
        return repo.save(product);
    }
    @GetMapping("/products")
    public Page<Product> all(
            @RequestParam(required = false, name="Name")String name,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "3")int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    )
    {
        PageRequest paging = PageRequest.of(page, size).withSort(sort.equalsIgnoreCase("ASC")?
                Sort.by("name").ascending():
                Sort.by("name").descending());

        Page<Product> res;
        if(name==null){
            res= repo.findAll(paging);
        }else{
            res= repo.findByNameContainingIgnoreCase(name,paging);
        }

        return res;
    }
    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Long id){
        Product match = repo.findById(id).orElseThrow();
        repo.delete(match);

    }
    @PutMapping("/products/{id}")
    public Product update(@PathVariable Long id, @RequestBody ProductUpdate productUpdate){
        Product forSave = new Product(id,productUpdate.getName(), productUpdate.getDescription(),productUpdate.getPrice());
        return repo.save(forSave);
    }

}
