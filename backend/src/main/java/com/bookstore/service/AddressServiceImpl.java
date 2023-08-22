package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.custom_exceptions.ResourceNotFoundException;
import com.bookstore.dto.AddressDTO;
import com.bookstore.dto.ApiResponse;
import com.bookstore.dto.DetachedAddressDTO;
import com.bookstore.entities.Address;
import com.bookstore.entities.User;
import com.bookstore.repository.AddressRepository;
import com.bookstore.repository.UserRepository;


@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addAddress(Long userId, AddressDTO addressDTO) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("invalid user id!"));
		Address address = mapper.map(addressDTO, Address.class);
		address.setUser(user);
		addressRepository.save(address);
		ApiResponse apiResponse = new ApiResponse("Address ADDED");
		return apiResponse;
	}

	
	@Override
	public List<DetachedAddressDTO> getAddress(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("invalid user id!"));
		List<DetachedAddressDTO> addressList = new ArrayList<>();
		user.getAddressList().forEach(i -> addressList.add(mapper.map(i, DetachedAddressDTO.class)));
		return addressList;
	}

//	@Override
//	public ApiResponse deleteAddress(Long userId, Long addressId) {
//		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("invalid user id!"));
//		List<Address> addressList = user.getAddressList();
//		for(int i = 0; i < addressList.size(); i++) {
//			if(addressList.get(i).getId() == Integer.parseInt(Long.toString(addressId)))
//				addressList.remove(i);
//		}
//		return new ApiResponse("Address "+addressId+" Deleted Successfully for userId " + userId );
//	}
	
	@Override
	public ApiResponse updateAddress(Long addressId, AddressDTO addressDTO) {
		Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("inconsistent data error"));
		address.setCity(addressDTO.getCity());
		address.setCountry(addressDTO.getCountry());
		address.setLandmark(addressDTO.getLandmark());
		address.setState(addressDTO.getState());
		address.setStreet(addressDTO.getStreet());
		address.setZipcode(addressDTO.getZipcode());
		return new ApiResponse("Address "+addressId+" Updated Successfully!");
	}


	@Override
	public ApiResponse deleteAddress(Long addressId) {
		Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("inconsistent data error"));
		addressRepository.delete(address);
		return new ApiResponse("Address "+addressId+" Deleted Successfully");
	}
}
