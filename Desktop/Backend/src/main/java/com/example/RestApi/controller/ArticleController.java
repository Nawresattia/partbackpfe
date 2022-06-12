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
 import com.example.RestApi.repository.ArticleRepository;
 


@CrossOrigin(origins = "*")
@RestController
public class ArticleController {
	private final ArticleRepository articlerepo;

	ArticleController(ArticleRepository articlerepo) {
		this.articlerepo = articlerepo;
	}


	
	@CrossOrigin(origins = "*")
	@GetMapping("/AllArticle")
	public  List<Article> AllArticle() {
		return articlerepo.findAll();
		
	}
	
	
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/ArticleById")
	public Optional<Article> ArticleById(@RequestBody Map<String, String> us) {
		Optional<Article> rec = articlerepo.findById(Long.parseLong(us.get("id")));
		return rec;
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/CreateArticle")
	public Map<String, String> CreateArticle(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Article article = new Article();
		article.setCode(us.get("code"));
		
		Date Datecreation;
		try {
			Datecreation = new SimpleDateFormat("yyyy-MM-dd").parse(us.get("Datecreation"));
			article.setDatecreation(Datecreation);
		} catch (ParseException e) {
			map.put("key", "error");
			return map;
		}
		
 		//fourrepo.findById(Long.parseLong(id))
		List<String> lst = List.of(us.get("Fournisseur").split(","));
		article.setFournisseurs(lst);
		article.setCode(us.get("code"));
		article.setIntitule(us.get("Intitule"));
		articlerepo.save(article);
		map.put("key", "done");
		return map;
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/DeleteArticle")
	public Map<String, String> DeleteArticle(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Article article = articlerepo.findByIddata(Long.parseLong(us.get("id")));		
		articlerepo.delete(article);
		map.put("key", "done");
		return map;
	}

	
	@CrossOrigin(origins = "*")
	@PostMapping("/UpdateArticle")
	public Map<String, String> UpdateArticle(@RequestBody Map<String, String> us) {
		HashMap<String, String> map = new HashMap<>();
		Article article = articlerepo.findByIddata(Long.parseLong(us.get("id")));	
		if(article!=null) {
			if(us.get("code")!=null && us.get("code")!="")
				article.setCode(us.get("code"));
			Date datecreation;
			try {
				//date="2022-12-25";
				if(us.get("date")!=null && us.get("datecreation")!="")
				{datecreation = new SimpleDateFormat("yyyy-MM-dd").parse(us.get("datecreation"));
				article.setDatecreation(datecreation);}
			} catch (ParseException e) {
				map.put("key", "error");
				return map;
			}
				if(us.get("fournisseurs")!=null && us.get("fournisseurs")!="")
				{List<String> lst = List.of(us.get("fournisseurs").split(","));
				article.setFournisseurs(lst);}
				if(us.get("Intitule")!=null && us.get("Intitule")!="")
				article.setIntitule(us.get("Intitule"));
				if(us.get("code")!=null && us.get("code")!="")
				article.setCode(us.get("code"));
				articlerepo.save(article);
				map.put("key", "done");
				return map;
		}
		map.put("key", "not Found");
		return map;
	}
	
	

	
	
}
