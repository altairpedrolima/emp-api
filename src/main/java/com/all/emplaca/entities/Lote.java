package com.all.emplaca.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.all.emplaca.enums.EstadoLote;

@Entity
public class Lote implements Serializable{

	private static final long serialVersionUID = 4780447495743154454L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String codigo;
	
	@Column(name = "quantidade_blanks_solicitados")
	private Long quantidadeBlanksSolicitados;
	
	@Column(name = "quantidade_blanks_gerados")
	private Long quantidadeBlanksGerados;
	
	@Column(name = "data_hora_registro")
	private LocalDateTime dataHoraRegistro;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado_lote", nullable = false)
	private EstadoLote estadoLote;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_fabricante")
	private Fabricante fabricante;
	
	//TODO Associar lote com blanks
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
	private List<Blanks> blanks;
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getQuantidadeBlanksSolicitados() {
		return quantidadeBlanksSolicitados;
	}

	public void setQuantidadeBlanksSolicitados(Long quantidadeBlanksSolicitados) {
		this.quantidadeBlanksSolicitados = quantidadeBlanksSolicitados;
	}

	public Long getQuantidadeBlanksGerados() {
		return quantidadeBlanksGerados;
	}

	public void setQuantidadeBlanksGerados(Long quantidadeBlanksGerados) {
		this.quantidadeBlanksGerados = quantidadeBlanksGerados;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dataHoraRegistro == null) ? 0 : dataHoraRegistro.hashCode());
		result = prime * result + ((estadoLote == null) ? 0 : estadoLote.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((quantidadeBlanksGerados == null) ? 0 : quantidadeBlanksGerados.hashCode());
		result = prime * result + ((quantidadeBlanksSolicitados == null) ? 0 : quantidadeBlanksSolicitados.hashCode());
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
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
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
		if (quantidadeBlanksGerados == null) {
			if (other.quantidadeBlanksGerados != null)
				return false;
		} else if (!quantidadeBlanksGerados.equals(other.quantidadeBlanksGerados))
			return false;
		if (quantidadeBlanksSolicitados == null) {
			if (other.quantidadeBlanksSolicitados != null)
				return false;
		} else if (!quantidadeBlanksSolicitados.equals(other.quantidadeBlanksSolicitados))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lote [id=" + id + ", codigo=" + codigo + ", quantidadeBlanksSolicitados=" + quantidadeBlanksSolicitados
				+ ", quantidadeBlanksGerados=" + quantidadeBlanksGerados + ", dataHoraRegistro=" + dataHoraRegistro
				+ ", estadoLote=" + estadoLote + ", fabricante=" + fabricante + "]";
	}
	

	
	
	
	
}