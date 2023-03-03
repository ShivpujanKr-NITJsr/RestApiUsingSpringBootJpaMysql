package com.spnk.mypkg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spnk.mypkg.dao.ServiceRepository;
import com.spnk.mypkg.entity.ModelClass;

@RestController
public class Controller {
	
	

	@Autowired
	private ServiceRepository serviceRepository;
	
	
	@GetMapping("/students")
	public ResponseEntity<List<ModelClass>> getAllEntity(){
		
		List<ModelClass> list =(List<ModelClass>) this.serviceRepository.findAll();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<ModelClass> getEntity(@PathVariable int id){
		
		try {
		ModelClass modelClass=this.serviceRepository.findById(id);
		 return ResponseEntity.ok().body(modelClass);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@PostMapping("/students")
	public ResponseEntity<String> createEntity(@RequestBody ModelClass modelClass){
		
		try {
			if(serviceRepository.find(modelClass)){
				return ResponseEntity.ok().body("already this id is present");
			}
			this.serviceRepository.save(modelClass);
			 return ResponseEntity.ok().body("saved successfully");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity
		            .badRequest() // Set the HTTP status code to indicate a bad request
		            .body("something went wrong,please be sure data is right");
		}
		
		
	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity<String> updateEntity(@RequestBody ModelClass modelClass,@PathVariable int id){
		
		try {
			if(!serviceRepository.find(modelClass,id)){
				
				return ResponseEntity.ok().body("this id is not present");
				
			}
			this.serviceRepository.save(modelClass);
			 return ResponseEntity.ok().body("updated successfully");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity
		            .badRequest() // Set the HTTP status code to indicate a bad request
		            .body("something went wrong,please be sure data is right");
		}
		
		
	}
	@DeleteMapping("/students/{id}")
	
	public ResponseEntity<String> deleteEntity(@PathVariable int id){
		
		try {
			
				
				this.serviceRepository.delete(this.serviceRepository.findById(id));
				 return ResponseEntity.ok().body("deleted successfully");
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity
		            .badRequest() // Set the HTTP status code to indicate a bad request
		            .body("something went wrong,please be sure id is present");
		}
		
		
	}
}
