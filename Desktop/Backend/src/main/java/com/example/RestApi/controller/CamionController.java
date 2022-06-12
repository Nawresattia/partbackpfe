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

import com.example.RestApi.entities.Article;
import com.example.RestApi.entities.Camion;
import com.example.RestApi.repository.CamionRepository;

 
@CrossOrigin(origins = "*")
@RestController
public class CamionController {

	private final CamionRepository camionrepo;

	CamionController(CamionRepository camionrepo) {
		this.camionrepo = camionrepo;
	}


	@CrossOrigin(origins = "*")
	@GetMapping("/AllCamion")
	public  List<Camion> AllCamion() {
		return camionrepo.findAll();
	}
	@CrossOrigin(origins = "*")
	@PostMapping("/CamionById")
	public Optional<Camion> ArticleById(@RequestBody Map<String, String> us) {
		Optional<Camion> rec = camionrepo.findById(Long.parseLong(us.get("id")));
		return rec;
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/CreateCamion")
	public Map<String, String> CreateCamion(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Camion camion = new Camion();
		camion.setImmatricule(us.get("Immatricule"));
		
		Date Datecreation;
		try {
			Datecreation = new SimpleDateFormat("yyyy-MM-dd").parse(us.get("Datecreation"));
			camion.setDatecreation(Datecreation);
		} catch (ParseException e) {
			map.put("key", "error");
			return map;
		}
		
 		//fourrepo.findById(Long.parseLong(id))
		List<String> lst = List.of(us.get("Chauffeur").split(","));
		camion.setChauffeur(lst);
		camion.setImmatricule(us.get("Imatricule"));
		camion.setType(us.get("Type"));
		camionrepo.save(camion);
		map.put("key", "done");
		return map;
	}

	

	@CrossOrigin(origins = "*")
	@PostMapping("/DeleteCamion")
	public Map<String, String> DeleteCamion(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Camion camion = camionrepo.findByIddata(Long.parseLong(us.get("id")));		
		camionrepo.delete(camion);
		map.put("key", "done");
		return map;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/UpdateCamion")
	public Map<String, String> UpdateCamion(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Camion camion = camionrepo.findByIddata(Long.parseLong(us.get("id")));	
		if(camion!=null) {
			if(us.get("Immatricule")!=null && us.get("Immatricule")!="")
				camion.setImmatricule(us.get("Immatricule"));
			Date datecreation;
			try {
				//date="2022-12-25";
				if(us.get("date")!=null && us.get("datecreation")!="")
				{datecreation = new SimpleDateFormat("yyyy-MM-dd").parse(us.get("datecreation"));
				camion.setDatecreation(datecreation);}
			} catch (ParseException e) {
				map.put("key", "error");
				return map;
			}
				if(us.get("chauffeur")!=null && us.get("chauffeur")!="")
				{List<String> lst = List.of(us.get("chauffeur").split(","));
				camion.setChauffeur(lst);}
				if(us.get("Type")!=null && us.get("Type")!="")
				camion.setType(us.get("Type"));
				if(us.get("taille")!=null && us.get("taille")!="")
				camion.setTaille(us.get("taille"));
				camionrepo.save(camion);
				map.put("key", "done");
				return map;
		}
		map.put("key", "not Found");
		return map;
	}
	
	

			 
	
}
