package com.msf.rest.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Table(name="Products")
@Data
public class Product implements Serializable{
	
	private static final long serialVersionUID = -4747635346806802214L;

	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="name", nullable= false)
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy="product", targetEntity=Image.class,cascade = CascadeType.REMOVE,orphanRemoval=true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Image> images;
	
	@ManyToOne
	@JoinColumn(name="parent_product_id")
	private Product product;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Product> childProducts;

}