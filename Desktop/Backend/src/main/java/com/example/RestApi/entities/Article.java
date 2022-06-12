package com.example.RestApi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "article")
public class Article implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name="code")
	private String code;
	
	
	@Column(name="intitule")
	private String intitule;

	//@JsonIgnore
	//@OneToMany(mappedBy = "Fournisseur")
	@Column(name="fournisseur")
	@ElementCollection(targetClass=String.class)
	private List<String> fournisseurs;
	
	
	
	@Column(name="datecreation")
	private Date datecreation ;
	
	
	
	public Article(Long id, String code, String intitule, List<String> fournisseurs, Date datecreation) {
		
		this.id = id;
		this.code = code;
		this.intitule = intitule;
		this.fournisseurs = fournisseurs;
		this.datecreation = datecreation;
	}
	public Article() {}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIntitule() {
		return this.intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public List<String> getFournisseurs() {
		return this.fournisseurs;
	}
	public void setFournisseurs(List<String> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}
	public Date getDatecreation() {
		return this.datecreation;
	}
	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	
	
	
}
