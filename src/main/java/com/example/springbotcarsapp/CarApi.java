package com.example.springbotcarsapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;
import javax.websocket.server.PathParam;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/cars")
public class CarApi {

    private List<Car> carList;

    public CarApi() {
        this.carList = new ArrayList<>();
        carList.add(new Car(1L, "Ford", "Mustang", "czarny"));
        carList.add(new Car(2L, "Renault", "Laguna", "czerwony"));
        carList.add(new Car(3L, "VW", "Transit", "biały"));


    }

//    @GetMapping
//    public ResponseEntity<List<Car>> getCars() {
//        return new ResponseEntity<>(carList, OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarsById(@PathVariable long id) {
        Optional<Car> first = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (first.isPresent()) {
            return new ResponseEntity<>(first.get(), OK);

        }
        return new ResponseEntity<>(NOT_FOUND);

    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars(@RequestParam(required = false, defaultValue = "") String kolor) {
        if (kolor.isEmpty()) {
            return new ResponseEntity<>(carList, HttpStatus.OK);
        } else {
            Predicate<Car> byColor = car -> car.getKolor().equals(kolor);
            List<Car> cars = carList.stream().filter(byColor).collect(Collectors.toList());
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }

    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        boolean add = carList.add(car);
        if (add) {
            return new ResponseEntity<>(CREATED);
        }
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity modCarsById(@RequestBody Car newCar) {
        Optional<Car> first = carList.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
        if (first.isPresent()) {
            carList.remove(first.get());
            carList.add(newCar);
            return new ResponseEntity<>(CREATED);
        }

        return new ResponseEntity<>(NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar (@PathVariable long id) {
        Optional<Car> first = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (first.isPresent()) {
            carList.remove(first.get());
            return new ResponseEntity<>(OK);
        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity modifyCarField(@PathVariable int id,  @RequestBody Map<Object, Object> carFields) {
        Optional<Car> findCar = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (findCar.isPresent()) {
            carFields.forEach((key, value) -> {
                Field carField = ReflectionUtils.findField(Car.class, (String) key);
                assert carField != null;
                carField.setAccessible(true);
                ReflectionUtils.setField(carField, findCar.get(), value);
            });
            return new ResponseEntity<>(findCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}



