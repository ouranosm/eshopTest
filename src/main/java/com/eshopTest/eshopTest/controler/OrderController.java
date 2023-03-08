package com.eshopTest.eshopTest.controler;

import com.eshopTest.eshopTest.entity.Order;
import com.eshopTest.eshopTest.entity.Product;
import com.eshopTest.eshopTest.repository.OrderRepository;
import com.eshopTest.eshopTest.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Failed to retrieve order with id: " + orderId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Order addOrder(@RequestBody Order order){
        Set<Product> products = new HashSet<>();
        for (Product p : order.getProducts()) {
            // Retrieve the product from the database using its id
            Product product = productRepository.findById(p.getId())
                    .orElseThrow(() -> new RuntimeException("Failed to retrieve product with id: " + p.getId()));
            products.add(product);
        }
        // Set the set of products to the order object
        order.setProducts(products);
        return orderRepository.save(order);
    }
    @GetMapping
    public Page<Order> all(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "3")int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort
    )
    {
        PageRequest paging = PageRequest.of(page, size).withSort(sort.equalsIgnoreCase("ASC")?
                Sort.by("name").ascending():
                Sort.by("name").descending());

        return orderRepository.findAll(paging);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Order match = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(match);
    }

    @GetMapping("/{orderId}/products")
    public Set<Product> getOrderProducts(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve order with id: " + orderId));
        return order.getProducts();
    }


    /*
    @PutMapping("/orders/{id}")
    public Order update(@PathVariable Long id, @RequestBody OrderUpdate orderUpdate){
        Product forSave = new Order(id,orderUpdate.getName(),
                orderUpdate.getDescription(),orderUpdate.getPrice());
        return repo.save(forSave);*/
    }