package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.dto.AddressDTO;
import com.bookstore.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	public AddressService addressService;
	
	@PostMapping("/addtouser/{userId}")
	public ResponseEntity<?> addressDetails(@PathVariable Long userId, @RequestBody AddressDTO addressDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(addressService.addAddress(userId, addressDTO));
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getAddress(@PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.FOUND).body(addressService.getAddress(userId));
	}
	
	@DeleteMapping("/addressid/{addressId}")
	public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) {
		return ResponseEntity.status(HttpStatus.FOUND).body(addressService.deleteAddress(addressId));
	}
	
	@PutMapping("/addressid/{addressId}")
	public ResponseEntity<?>  updateAddress(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO) {
		return ResponseEntity.status(HttpStatus.FOUND).body(addressService.updateAddress(addressId, addressDTO));
	}
	

}
