package com.Spring;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="product")
public class product 
{	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")	
	private  int prid;

	
	@Column(name="prnm")
	private String prnm;
	
	
	@Column(name="price")
	private int price;
	
	@Column(name="descrip")
	private String descrip;
	
	
	@Column(name="qty")
	private int qty;
	
	
	@Column(name="reorder")
	private int reorder;

	
	@Column(name="pic")
	private byte[] pic;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	private category ct;


	public int getPrid() {
		return prid;
	}


	public void setPrid(int prid) {
		this.prid = prid;
	}


	public String getPrnm() {
		return prnm;
	}


	public void setPrnm(String prnm) {
		this.prnm = prnm;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getDescrip() {
		return descrip;
	}


	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public int getReorder() {
		return reorder;
	}


	public void setReorder(int reorder) {
		this.reorder = reorder;
	}


	public byte[] getPic() {
		return pic;
	}


	public void setPic(byte[] pic) {
		this.pic = pic;
	}


	public category getCt() {
		return ct;
	}


	public void setCt(category ct) {
		this.ct = ct;
	}
				
}
