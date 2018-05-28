package com.all.emplaca.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ClienteWs implements Serializable{

	private static final long serialVersionUID = 4780447495743154454L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, 
			orphanRemoval=true)
	private List<CertificadoDigital> certificadosDigitais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<CertificadoDigital> getCertificadosDigitais() {
		return certificadosDigitais;
	}

	public void setCertificadosDigitais(List<CertificadoDigital> certificadosDigitais) {
		this.certificadosDigitais = certificadosDigitais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((certificadosDigitais == null) ? 0 : certificadosDigitais.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		ClienteWs other = (ClienteWs) obj;
		if (certificadosDigitais == null) {
			if (other.certificadosDigitais != null)
				return false;
		} else if (!certificadosDigitais.equals(other.certificadosDigitais))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClienteWs [id=" + id + ", nome=" + nome + ", certificadosDigitais=" + certificadosDigitais + "]";
	}
	
	

}