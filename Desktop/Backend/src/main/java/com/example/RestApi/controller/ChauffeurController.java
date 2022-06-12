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

import com.example.RestApi.entities.Chauffeur;
import com.example.RestApi.repository.ChauffeurRepository;


 
@CrossOrigin(origins = "*")
@RestController
public class ChauffeurController {
	private final ChauffeurRepository chauffeurrepo;

	ChauffeurController(ChauffeurRepository chauffeurrepo) {
		this.chauffeurrepo = chauffeurrepo;
	}


	@CrossOrigin(origins = "*")
	@GetMapping("/AllChauffeur")
	public  List<Chauffeur> AllChauffeur() {
		return chauffeurrepo.findAll();
	}
	@CrossOrigin(origins = "*")
	@PostMapping("/ChauffeurById")
	public Optional<Chauffeur> ArticleById(@RequestBody Map<String, String> us) {
		Optional<Chauffeur> rec = chauffeurrepo.findById(Long.parseLong(us.get("id")));
		return rec;
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/CreateChauffeur")
	public Map<String, String> CreateChauffeur(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Chauffeur chauffeur = new Chauffeur();
		chauffeur.setNomchauffeur(us.get("Nomchauffeur"));
		
		Date createdat;
		try {
			createdat = new SimpleDateFormat("yyyy-MM-dd").parse(us.get("createdat"));
			chauffeur.setCreatedat(createdat);
		} catch (ParseException e) {
			map.put("key", "error");
			return map;
		}
		
 		//fourrepo.findById(Long.parseLong(id))
 		chauffeur.setNomchauffeur(us.get("nomchauffeur"));
		chauffeur.setPrenomchauffeur(us.get("prenomchauffeur"));
		chauffeurrepo.save(chauffeur);
		map.put("key", "done");
		return map;
	}

	

	@CrossOrigin(origins = "*")
	@PostMapping("/DeleteChauffeur")
	public Map<String, String> DeleteChauffeur(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Chauffeur chauffeur = chauffeurrepo.findByIddata(Long.parseLong(us.get("id")));		
		chauffeurrepo.delete(chauffeur);
		map.put("key", "done");
		return map;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/UpdateChauffeur")
	public Map<String, String> UpdateChauffeur(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Chauffeur chauffeur = chauffeurrepo.findByIddata(Long.parseLong(us.get("id")));	
		if(chauffeur!=null) {
			if(us.get("Nomchauffeur")!=null && us.get("nomchauffeur")!="")
				chauffeur.setNomchauffeur(us.get("nomchauffeur"));
			Date createdat;
			try {
				//date="2022-12-25";
				if(us.get("createdat")!=null && us.get("createdat")!="")
				{createdat = new SimpleDateFormat("yyyy-MM-dd").parse(us.get("createdat"));
				chauffeur.setCreatedat(createdat);}
			} catch (ParseException e) {
				map.put("key", "error");
				return map;
			}
 				if(us.get("Nomchauffeur")!=null && us.get("Nomchauffeur")!="")
				chauffeur.setNomchauffeur(us.get("Nomchauffeur"));
				if(us.get("prenomchauffeur")!=null && us.get("prenomchauffeur")!="")
				chauffeur.setPrenomchauffeur(us.get("prenomchauffeur"));
				chauffeurrepo.save(chauffeur);
				map.put("key", "done");
				return map;
		}
		map.put("key", "not Found");
		return map;
	}
}
	