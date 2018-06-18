package com.all.emplaca.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.all.emplaca.enums.EstadoLote;
import com.fasterxml.jackson.annotation.JsonIgnore;

// implements Identifiable<Long>

@Entity
public class Lote implements Serializable {
	private static final long serialVersionUID = 1591421672797784393L;

	public Lote(Long quantidadeBlanksSolicitados, Fabricante fabricante, String numeroLote, EstadoLote estadoLote,
			LocalDateTime dataHoraRegistro) {
		super();
		this.quantidadeDeBlanksSolicitados = quantidadeBlanksSolicitados;
		this.fabricante = fabricante;
		this.numeroLote = numeroLote;
		this.estadoLote = estadoLote;
		this.dataHoraRegistro = dataHoraRegistro;
	}

	public Lote() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String numeroLote;

	@Column(name = "quantidade_blanks_solicitados")
	private Long quantidadeDeBlanksSolicitados;

	@Column(name = "quantidade_blanks_gerados")
	private Long quantidadeDeBlanksGerados;

	@Column(name = "data_hora_registro")
	private LocalDateTime dataHoraRegistro;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_lote")
	private EstadoLote estadoLote;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_fabricante")
	private Fabricante fabricante;

	// TODO Associar lote com blanks

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
	// orphanRemoval=true)
	
	// @OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL)
	

	@OneToMany(mappedBy = "lote", cascade = { CascadeType.ALL })
	private List<Blank> blanks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Long getQuantidadeDeBlanksSolicitados() {
		return quantidadeDeBlanksSolicitados;
	}

	public void setQuantidadeDeBlanksSolicitados(Long quantidadeDeBlanksSolicitados) {
		this.quantidadeDeBlanksSolicitados = quantidadeDeBlanksSolicitados;
	}

	public Long getQuantidadeDeBlanksGerados() {
		return quantidadeDeBlanksGerados;
	}

	public void setQuantidadeDeBlanksGerados(Long quantidadeDeBlanksGerados) {
		this.quantidadeDeBlanksGerados = quantidadeDeBlanksGerados;
	}

	public LocalDateTime getDataHoraRegistro() {
		return dataHoraRegistro;
	}

	public void setDataHoraRegistro(LocalDateTime dataHoraRegistro) {
		this.dataHoraRegistro = dataHoraRegistro;
	}

	public EstadoLote getEstadoLote() {
		return estadoLote;
	}

	public void setEstadoLote(EstadoLote estadoLote) {
		this.estadoLote = estadoLote;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Blank> getBlanks() {
		return Optional.ofNullable(blanks).orElse(Collections.emptyList());		
		//return blanks;
	}

	public void setBlanks(List<Blank> blanks) {
		this.blanks = blanks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataHoraRegistro == null) ? 0 : dataHoraRegistro.hashCode());
		result = prime * result + ((estadoLote == null) ? 0 : estadoLote.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroLote == null) ? 0 : numeroLote.hashCode());
		result = prime * result + ((quantidadeDeBlanksGerados == null) ? 0 : quantidadeDeBlanksGerados.hashCode());
		result = prime * result
				+ ((quantidadeDeBlanksSolicitados == null) ? 0 : quantidadeDeBlanksSolicitados.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lote other = (Lote) obj;
		if (dataHoraRegistro == null) {
			if (other.dataHoraRegistro != null)
				return false;
		} else if (!dataHoraRegistro.equals(other.dataHoraRegistro))
			return false;
		if (estadoLote != other.estadoLote)
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroLote == null) {
			if (other.numeroLote != null)
				return false;
		} else if (!numeroLote.equals(other.numeroLote))
			return false;
		if (quantidadeDeBlanksGerados == null) {
			if (other.quantidadeDeBlanksGerados != null)
				return false;
		} else if (!quantidadeDeBlanksGerados.equals(other.quantidadeDeBlanksGerados))
			return false;
		if (quantidadeDeBlanksSolicitados == null) {
			if (other.quantidadeDeBlanksSolicitados != null)
				return false;
		} else if (!quantidadeDeBlanksSolicitados.equals(other.quantidadeDeBlanksSolicitados))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lote [id=");
		builder.append(id);
		builder.append(", numeroLote=");
		builder.append(numeroLote);
		builder.append(", quantidadeDeBlanksSolicitados=");
		builder.append(quantidadeDeBlanksSolicitados);
		builder.append(", quantidadeDeBlanksGerados=");
		builder.append(quantidadeDeBlanksGerados);
		builder.append(", dataHoraRegistro=");
		builder.append(dataHoraRegistro);
		builder.append(", estadoLote=");
		builder.append(estadoLote);
		builder.append(", fabricante=");
		builder.append(fabricante);
		builder.append("]");
		return builder.toString();
	}

}