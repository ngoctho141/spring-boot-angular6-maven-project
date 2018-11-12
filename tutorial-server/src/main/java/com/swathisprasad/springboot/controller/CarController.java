
package com.swathisprasad.springboot.controller;

import com.swathisprasad.springboot.Car;
import com.swathisprasad.springboot.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Swathi
 *
 */
@RestController
@RequestMapping("/api")
public class CarController {

	private CarRepository repository;

	@Autowired
	public void setRepository(CarRepository repository) {
		this.repository = repository;
	}

	@GetMapping()
	public Collection<Car> getAllCars(){
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public HttpEntity<Car> getEditCar(@PathVariable("id") Long id){
		Car editCar = null;
		Optional<Car> carOptional = repository.findById(id);
		if(carOptional.isPresent()) {
			editCar = carOptional.get();
			editCar.add(linkTo(methodOn(CarController.class).getEditCar(id)).withSelfRel());
		}
		return new ResponseEntity<>(editCar, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deleteCar(@PathVariable("id")Long id){
		repository.deleteById(id);
	}

	@PutMapping("/{id}")
	public void updateCar(@PathVariable("id")Long id, @RequestBody Car updateCar){
		Car car;
		Optional<Car> carOptional = repository.findById(id);
		if(carOptional.isPresent()) {
			car = carOptional.get();
			car.setName(updateCar.getName());
			repository.save(car);
		}
	}

	@PostMapping()
	public void insertCar(@RequestBody Car newCar){
		repository.save(newCar);
	}

}
