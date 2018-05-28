package com.all.emplaca.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.all.emplaca.utils.Mensagens;
import com.all.emplaca.utils.TamanhosCampos;

@Entity
public class Endereco implements Serializable{

	private static final long serialVersionUID = -6392633658877858829L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@Size(max = 8, message = Mensagens.TAMANHO_CEP_INVALIDO)
	private String cep;

	@Column(columnDefinition = "TEXT")
	@Size(max = TamanhosCampos.TAMANHO_TEXTO_MAXIMO, message = Mensagens.TAMANHO_TEXTO_FORA_DO_PERMITIDO)
	private String logradouro;

	@Column(columnDefinition = "TEXT")
	@Size(max = TamanhosCampos.TAMANHO_TEXTO_MAXIMO, message = Mensagens.TAMANHO_TEXTO_FORA_DO_PERMITIDO)
	private String complemento;

	@Column
	@Size(max = TamanhosCampos.TAMANHO_TEXTO_MAXIMO, message = Mensagens.TAMANHO_TEXTO_FORA_DO_PERMITIDO)
	private String bairro;

	@Column(columnDefinition = "TEXT")
	@Size(max = TamanhosCampos.TAMANHO_TEXTO_MAXIMO, message = Mensagens.TAMANHO_TEXTO_FORA_DO_PERMITIDO)
	private String localidade;

	@Column
	@Size(max = TamanhosCampos.TAMANHO_TEXTO_MAXIMO, message = Mensagens.TAMANHO_TEXTO_FORA_DO_PERMITIDO)
	private String unidade;

	@Column
	@Size(max = TamanhosCampos.TAMANHO_TEXTO_MAXIMO, message = Mensagens.TAMANHO_TEXTO_FORA_DO_PERMITIDO)
	private String ibge;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((ibge == null) ? 0 : ibge.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localidade == null) ? 0 : localidade.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (ibge == null) {
			if (other.ibge != null)
				return false;
		} else if (!ibge.equals(other.ibge))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localidade == null) {
			if (other.localidade != null)
				return false;
		} else if (!localidade.equals(other.localidade))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", localidade=" + localidade + ", unidade=" + unidade + ", ibge=" + ibge + "]";
	}
	
	
	

}
