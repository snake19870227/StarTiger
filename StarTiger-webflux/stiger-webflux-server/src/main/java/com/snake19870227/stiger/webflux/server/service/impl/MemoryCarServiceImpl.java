package com.snake19870227.stiger.webflux.server.service.impl;

import com.snake19870227.stiger.webflux.server.entity.dto.Car;
import com.snake19870227.stiger.webflux.server.exception.CarNotFoundException;
import com.snake19870227.stiger.webflux.server.service.CarService;
import org.springframework.beans.factory.InitializingBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Bu HuaYang
 */
public class MemoryCarServiceImpl implements CarService, InitializingBean {

    private final Map<Integer, Car> carMap = new ConcurrentHashMap<>();

    @Override
    public Flux<Car> list() {
        return Flux.fromIterable(carMap.values());
    }

    @Override
    public Flux<Car> getByIds(Flux<Integer> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(carMap.get(id)));
    }

    @Override
    public Mono<Car> getById(Integer id) {
        return Mono.justOrEmpty(carMap.get(id))
                .switchIfEmpty(Mono.error(new CarNotFoundException()));
    }

    @Override
    public Flux<Car> createOrUpdate(Flux<Car> cars) {
        return cars.doOnNext(car -> carMap.put(car.getId(), car));
    }

    @Override
    public Mono<Car> createOrUpdate(Car car) {
        carMap.put(car.getId(), car);
        return Mono.just(car);
    }

    @Override
    public Mono<Car> delete(Integer id) {
        return Mono.justOrEmpty(carMap.remove(id));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        carMap.put(1, new Car(1, "Audi", "A1", new BigDecimal("20.35")));
        carMap.put(2, new Car(2, "Audi", "A3", new BigDecimal("30.75")));
        carMap.put(3, new Car(3, "Audi", "A5", new BigDecimal("39.8")));
    }
}
