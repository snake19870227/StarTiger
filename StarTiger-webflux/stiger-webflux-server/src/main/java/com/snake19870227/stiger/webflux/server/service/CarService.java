package com.snake19870227.stiger.webflux.server.service;

import com.snake19870227.stiger.webflux.server.entity.dto.Car;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Bu HuaYang
 */
public interface CarService {

    Flux<Car> list();

    Flux<Car> getByIds(Flux<Integer> ids);

    Mono<Car> getById(Integer id);

    Flux<Car> createOrUpdate(Flux<Car> users);

    Mono<Car> createOrUpdate(Car user);

    Mono<Car> delete(Integer id);
}
