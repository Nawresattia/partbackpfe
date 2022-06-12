package com.example.RestApi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.RestApi.entities.ParcRoulant;

import com.example.RestApi.repository.ParcRoulantRepository;

  
@CrossOrigin(origins = "*")
@RestController
public class ParcRoulantController {

	private final ParcRoulantRepository ParcRoulancerepo;

	ParcRoulantController(ParcRoulantRepository prr) {
		this.ParcRoulancerepo = prr;
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/AllParcRoulant")
	public  List<ParcRoulant> AllParcRoulant() {
		return ParcRoulancerepo.findAll();
	}
	@CrossOrigin(origins = "*")
	@PostMapping("/ParcRoulantById")
	public Optional<ParcRoulant> ParcRoulantById(@RequestBody Map<String, String> us) {
		Optional<ParcRoulant> rec = ParcRoulancerepo.findById(Long.parseLong(us.get("id")));
		return rec;
	}
	
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/CreateParcRoulant")
	public Map<String, String> CreateParcRoulant(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		ParcRoulant parcRoulant = new ParcRoulant();
		parcRoulant.setCode(us.get("Code"));
		
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(us.get("date"));
			parcRoulant.setDate(date);
		} catch (ParseException e) {
			map.put("key", "error");
			return map;
		}
		
 		//fourrepo.findById(Long.parseLong(id))cc
		List<String> lst = List.of(us.get("chauffeur").split(","));
		parcRoulant.setChaufeur(lst)  ;
		
		map.put("key", "done");
		return map;
	
}
	

	@CrossOrigin(origins = "*")
	@PostMapping("/DeleteParcRoulant")
	public Map<String, String> DeleteReception(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		ParcRoulant parcRoulant = ParcRoulancerepo.findByIddata(Long.parseLong(us.get("id")));		
		ParcRoulancerepo.delete(parcRoulant);
		map.put("key", "done");
		return map;
	}	
	
	
	 
	
}
