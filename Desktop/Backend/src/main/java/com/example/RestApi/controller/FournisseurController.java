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

 import com.example.RestApi.entities.Fournisseur;
 import com.example.RestApi.repository.FournisseurRepository;

 
 
@CrossOrigin(origins = "*")
@RestController
public class FournisseurController {
 
	private final FournisseurRepository fournisseurrepo;

	FournisseurController(FournisseurRepository fournisseurrepo) {
		this.fournisseurrepo = fournisseurrepo;
	}


	@CrossOrigin(origins = "*")
	@GetMapping("/AllFournisseur")
	public  List<Fournisseur> AllFournisseur() {
		return fournisseurrepo.findAll();
	}
	@CrossOrigin(origins = "*")
	@PostMapping("/FournisseurById")
	public Optional<Fournisseur> ArticleById(@RequestBody Map<String, String> us) {
		Optional<Fournisseur> rec = fournisseurrepo.findById(Long.parseLong(us.get("id")));
		return rec;
	}
	
	

	@CrossOrigin(origins = "*")
	@PostMapping("/CreateFournisseur")
	public Map<String, String> CreateFournisseur(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setNom(us.get("Nom"));
		Date Datecreation;
		try {
			Datecreation = new SimpleDateFormat("yyyy-MM-dd").parse(us.get("Datecreation"));
			fournisseur.setDatecreation(Datecreation);
		} catch (ParseException e) {
			map.put("key", "error");
			return map;
		}
		
 		//fourrepo.findById(Long.parseLong(id))
 		fournisseur.setNom(us.get("nom"));
		fournisseur.setCode(us.get("Code"));
		fournisseurrepo.save(fournisseur);
		map.put("key", "done");
		return map;
	}

	

	@CrossOrigin(origins = "*")
	@PostMapping("/DeleteFournisseur")
	public Map<String, String> DeleteFournisseur(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Fournisseur fournisseur = fournisseurrepo.findByIddata(Long.parseLong(us.get("id")));		
		fournisseurrepo.delete(fournisseur);
		map.put("key", "done");
		return map;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/UpdateFournisseur")
	public Map<String, String> UpdateFournisseur(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Fournisseur fournisseur = fournisseurrepo.findByIddata(Long.parseLong(us.get("id")));	
		if(fournisseur!=null) {
			if(us.get("Nom")!=null && us.get("nom")!="")
				fournisseur.setNom(us.get("nom"));
			Date Datecreation;
			try {
				//date="2022-12-25";
				if(us.get("Datecreation")!=null && us.get("Datecreation")!="")
				{Datecreation = new SimpleDateFormat("yyyy-MM-dd").parse(us.get("Datecreation"));
				fournisseur.setDatecreation(Datecreation);}
			} catch (ParseException e) {
				map.put("key", "error");
				return map;
			}
 				if(us.get("Nom")!=null && us.get("Nom")!="")
				fournisseur.setNom(us.get("Nom"));
				if(us.get("Type")!=null && us.get("Type")!="")
				fournisseur.setType(us.get("Type"));
				fournisseurrepo.save(fournisseur);
				map.put("key", "done");
				return map;
		}
		map.put("key", "not Found");
		return map;
	}
}
