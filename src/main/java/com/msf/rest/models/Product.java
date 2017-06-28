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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Products")
@XmlRootElement
public class Product implements Serializable{
	
	private static final long serialVersionUID = -4747635346806802214L;
	
	public Product(){
		
	}

	@Id 
	@Getter @Setter
	@SequenceGenerator(name = "PROD_ID_GENERATOR", sequenceName = "PROD_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROD_ID_GENERATOR")
	private int id;
	
	@Column(name="name", nullable= false)
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String description;
	
	@OneToMany(mappedBy="product", cascade = CascadeType.REMOVE,orphanRemoval=true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Getter @Setter
	private List<Image> images;
	
	@ManyToOne
	@JoinColumn(name="parent_product_id")
	@Getter @Setter
	private Product parentProduct;
	
	@OneToMany(mappedBy="parentProduct",cascade=CascadeType.ALL,orphanRemoval=true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Getter @Setter
	private List<Product> childProducts;

}