package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Merchandise;
import com.example.demo.repository.MerchandiseRepository;

@Service
public class MerchandiseService {
	
	@Autowired
	MerchandiseRepository merchandiseRepository;
	
	// Create
	public Merchandise createMerchandise(Merchandise merchandise) {
		return merchandiseRepository.save(merchandise);
	}
	
	//Read
	public List<Merchandise> getALLMerchandise(){
		return merchandiseRepository.findAll();
	}

	
	//Update
	
	public Merchandise updateMerchandise(String id, Merchandise merchandiseDetails) {
		Merchandise merchandise = merchandiseRepository.findById(id).get();
		merchandise.setName(merchandiseDetails.getName());
		merchandise.setPrice(merchandiseDetails.getPrice());
		merchandise.setQty(merchandiseDetails.getQty());
		merchandise.setImgurl(merchandiseDetails.getImgurl());
		
		return merchandiseRepository.save(merchandise);
	}
	
	//Delete
	public void deleteMerchandiseByid(String id) {
		merchandiseRepository.deleteById(id);
	}
	
	public void deleteMerchandise( Merchandise merchandise) {
		merchandiseRepository.delete(merchandise);
	}
	
	
}
