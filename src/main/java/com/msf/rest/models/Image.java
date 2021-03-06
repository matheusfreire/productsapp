package com.msf.rest.models;

import static com.msf.rest.util.TagUtil.*;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Images")
@XmlRootElement
public class Image implements Serializable{
	
	private static final long serialVersionUID = -7991881621478322926L;
	@Id 
    @Column(name = "id")
	@SequenceGenerator(name = "IMAGE_ID_GENERATOR", sequenceName = "IMAGE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_ID_GENERATOR")
	@Getter @Setter
    private int id;
	
	@Getter @Setter
	private String type;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	@Getter @Setter
	private Product product;
	
	public Image(){
		
	}
	
	public String toJson(){
		StringBuilder s = new StringBuilder();
		s.append(OPEN_KEYS);
		s.append(DOUBLE_QUOTE+ "id"+DOUBLE_QUOTE  +":" +this.id + COMMA);
		s.append(DOUBLE_QUOTE+ "type"+DOUBLE_QUOTE  +":" + DOUBLE_QUOTE +this.type + DOUBLE_QUOTE);
		s.append(CLOSE_KEYS);
		return s.toString();
	}

}