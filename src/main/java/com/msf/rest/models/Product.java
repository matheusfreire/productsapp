package com.msf.rest.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="products")
@Data
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4747635346806802214L;

	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="name", nullable= false)
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy="product", targetEntity=Image.class,cascade=CascadeType.ALL)
	private List<Image> images;

}