package com.snake19870227.stiger.webflux.server.controller;

import com.snake19870227.stiger.webflux.server.entity.dto.Car;
import com.snake19870227.stiger.webflux.server.service.CarService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author Bu HuaYang
 */
@RestController
@RequestMapping(path = "/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(path = "")
    public Flux<Car> list() {
        return carService.list();
    }

    @GetMapping(path = "/{id}")
    public Mono<Car> getById(@PathVariable Integer id){
        return carService.getById(id);
    }

    @PostMapping(path = "")
    public Flux<Car> create(@RequestBody Flux<Car> users){
        return carService.createOrUpdate(users);
    }

    @PutMapping(path = "/{id}")
    public Mono<Car> update(@PathVariable Integer id, @RequestBody Car car){
        Objects.requireNonNull(car);
        car.setId(id);
        return carService.createOrUpdate(car);
    }

    @DeleteMapping(path = "/{id}")
    public Mono<Car> delete(@PathVariable Integer id){
        return carService.delete(id);
    }
}
