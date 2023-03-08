package com.eshopTest.eshopTest.controler;

import com.eshopTest.eshopTest.entity.Order;
import com.eshopTest.eshopTest.entity.Product;
import com.eshopTest.eshopTest.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;


    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Failed to retrieve order with id: " + orderId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Order addOrder(@RequestBody Order order){

        Set<Product> products =
                order.getProducts();





        return orderRepository.save(order);



    }
    @GetMapping
    public Page<Order> all(
            @RequestParam(required = false, name="Name")String name,
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


    /*
    @PutMapping("/orders/{id}")
    public Order update(@PathVariable Long id, @RequestBody OrderUpdate orderUpdate){
        Product forSave = new Order(id,orderUpdate.getName(),
                orderUpdate.getDescription(),orderUpdate.getPrice());
        return repo.save(forSave);*/
    }