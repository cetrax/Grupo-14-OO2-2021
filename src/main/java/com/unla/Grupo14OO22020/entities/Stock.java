package com.unla.Grupo14OO22020.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idStock;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="stock")
	private Set<Lote> lotes=new HashSet<Lote>();
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_local", referencedColumnName = "idLocal")
    private Localito local;
	
	
	
	public Stock(int idStock, int cantidad,Set<Lote> lotes) {
		super();
		this.idStock = idStock;
		this.cantidad = cantidad;
		this.lotes=lotes;
	}
	
	public Stock() {}
	
	public Stock(int cantidad)
	{
		this.cantidad=cantidad;
	}
	
	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Set<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(Set<Lote> lotes) {
		this.lotes = lotes;
	}
	
	public Localito getLocal() {
		return local;
	}

	public void setLocal(Localito local) {
		this.local = local;
	}
	
	
	
	
	
}
