package com.Spring;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="category")
public class category
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private int catid;
	
	@Column(name="catnm")
	private String catnm;

	
	@OneToMany(mappedBy="ct",fetch = FetchType.LAZY)
	private List<product> PR;
	

	public List<product> getPR() {
		return PR;
	}

	public void setPR(List<product> pR) {
		PR = pR;
	}

	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	public String getCatnm() {
		return catnm;
	}

	public void setCatnm(String catnm) {
		this.catnm = catnm;
	}	
}
