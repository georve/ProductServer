package com.insurance.insuranceApp.controller;

import com.insurance.insuranceApp.dto.ProductDto;
import com.insurance.insuranceApp.model.Product;
import com.insurance.insuranceApp.repository.ProductRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Controller that serve a Product Entity
 * @author georman.calderon georve@gmail.com
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {



    @Autowired
    private ProductRepository service;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllStatistic() throws Exception{

        List<Product> list=service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Product>> getProductsByName(@PathVariable("name") String name) throws Exception{
        List<Product> list=service.findProductByName(name);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/keywords/{id}")
    public ResponseEntity<List<String>> getProdutsKeyword(@PathVariable("id") Integer id) throws Exception{
        Optional<Product> product= service.findById(id);
       List<Product> parents=processParent(product.get());
        List<String> keys=  parents.stream().map(x->x.getName()).collect(Collectors.toList());
        return ResponseEntity.ok(keys);
    }

    private List<Product> processParent(Product parent) {
        Optional<Product> product= service.findById(parent.getId());
        List<Product> products=new ArrayList<>();
        if(product.isPresent() && product.get().getParent()==null){
            products.add(parent);
            return products;
        }else{
            return processParent(parent.getParent());
        }
    }

    @GetMapping("level/{number}")
    public ResponseEntity<List<Integer>> getProductsByLevel(@PathVariable("number") Integer number) throws Exception{
        Optional<Product> product= service.findById(number);
        List<Product> parents=processParent(product.get());
        List<Integer> levels=  parents.stream().map(x->x.getLevel()).collect(Collectors.toList());
        return ResponseEntity.ok(levels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getAllDetails(@PathVariable("id") Integer id) {
        return service.findById(id).map(mapToPersonDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/siblings")
    public ResponseEntity<Set<ProductDto>> getAllSiblings(@PathVariable("id") Integer id) {
        return service.findById(id).map(findSiblings).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private Function<Product, Set<ProductDto>> products = person -> person.getParent().getChildren().stream()
            .map(p -> ProductDto.builder().id(p.getId()).name(p.getName()).level(p.getLevel()).build()).collect(Collectors.toSet());

    private Function<Product, Set<ProductDto>> findSiblings = person -> person.getParent().getChildren().stream()
            .map(p -> ProductDto.builder().id(p.getId()).name(p.getName()).level(p.getLevel()).build()).collect(Collectors.toSet());

    private Function<Product, ProductDto> mapToPersonDTO = p -> ProductDto.builder().id(p.getId()).name(p.getName()).parent(p.getParent()).children(p.getChildren()).build();


}
