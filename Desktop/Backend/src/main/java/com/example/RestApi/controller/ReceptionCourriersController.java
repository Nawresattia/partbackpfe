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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestApi.entities.ReceptionCourriers;
import com.example.RestApi.repository.ReceptionCourriersRepository;
@RestController
@RequestMapping
public class ReceptionCourriersController {
	private final ReceptionCourriersRepository receptionCourriersrepo;

	ReceptionCourriersController(ReceptionCourriersRepository receptionCourriersrepo) {
		this.receptionCourriersrepo = receptionCourriersrepo;
	}
	@CrossOrigin(origins = "*")
	@GetMapping("/AllReceptionCourriers")
	public  List<ReceptionCourriers> AllReceptionCourriers() {
		return receptionCourriersrepo.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/ReceptionCourriersById")
	public Optional<ReceptionCourriers> ReceptionById(@RequestBody Map<String, String> us) {
		Optional<ReceptionCourriers> rec = receptionCourriersrepo.findById(Long.parseLong(us.get("id")));
		return rec;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/CreateReceptionCourriers")
	public Map<String, String> CreateReceptionCourriers(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		ReceptionCourriers receptionCourriers = new ReceptionCourriers();
		receptionCourriers.setReference(us.get("reference"));
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(us.get("date"));
			receptionCourriers.setDate(date);
		} catch (ParseException e) {
			map.put("key", "error");
			return map;
		}
		
		//fourrepo.findById(Long.parseLong(id))
				List<String> lst = List.of(us.get("chauffeur").split(","));
				receptionCourriers.setChaufeur(lst);
				receptionCourriers.setReference(us.get("reference"));
				receptionCourriers.setEntrepot(us.get("Entrepot"));
				receptionCourriersrepo.save(receptionCourriers);
				map.put("key", "done");
				return map;
			}
	

	@CrossOrigin(origins = "*")
	@PostMapping("/DeleteReceptionCourriers")
	public Map<String, String> DeleteReceptionCourriers(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		ReceptionCourriers reception = receptionCourriersrepo.findByIddata(Long.parseLong(us.get("id")));		
		receptionCourriersrepo.delete(reception);
		map.put("key", "done");
		return map;
	}	
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/UpdateReceptionCourriers")
	public Map<String, String> UpdateReception(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		ReceptionCourriers reception = receptionCourriersrepo.findByIddata(Long.parseLong(us.get("id")));	
		if(reception!=null) {
			if(us.get("reference")!=null && us.get("reference")!="")
				reception.setReference(us.get("reference"));
				Date date;
				try {
					//date="2022-12-25";
					if(us.get("date")!=null && us.get("date")!="")
					{date = new SimpleDateFormat("yyyy-MM-dd").parse(us.get("date"));
					reception.setDate(date);}
				} catch (ParseException e) {
					map.put("key", "error");
					return map;
				}
				if(us.get("chauffeurs")!=null && us.get("chauffeurs")!="")
				{List<String> lst = List.of(us.get("chauffeurs").split(","));
				reception.setChaufeur(lst);}
				if(us.get("Entrepot")!=null && us.get("Entrepot")!="")
				reception.setEntrepot(us.get("Entrepot"));
				receptionCourriersrepo.save(reception);
				map.put("key", "done");
				return map;
		}
		map.put("key", "not Found");
		return map;
	}
	

	
	
		
}
