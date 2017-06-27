package com.msf.rest.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Images")
public class Image implements Serializable{
	
	private static final long serialVersionUID = -7991881621478322926L;
	@Id 
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter
    private int id;
	
	@Getter @Setter
	private String type;
	
	@Getter @Setter
	@ManyToOne
	private Product product;

}