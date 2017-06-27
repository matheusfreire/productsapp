package com.msf.rest.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Images")
@Data
public class Image implements Serializable{
	
	private static final long serialVersionUID = -7991881621478322926L;
	@Id 
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String type;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

}