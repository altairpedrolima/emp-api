package com.all.emplaca.api.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(allocationSize = 1, initialValue = 100000, name = "cliente_ws_id_seq", sequenceName = "cliente_ws_id_seq")
public abstract class ClienteWs extends EmplacaEntity {

	private static final long serialVersionUID = 6868737437804458402L;
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_ws_id_seq")
    private Long id;

    @Column(columnDefinition = "TEXT")
    //@Pattern(regexp = Regex.PROIBIDO_APENAS_ESPACOS, message = Mensagens.PROIBIDO_APENAS_ESPACOS)
    @NotEmpty(message="Nome não pode ser vazio")
    @Length(min = 5, max=100, message="Nome deve ter no minimo 5 e no maximo 100 caracteres")
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
    @JoinColumn(name = "id_cliente_ws")
    private List<CertificadoDigital> certificadosDigitais;


	@Override
	public Long getId() {
		return this.id;
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
		StringBuilder builder = new StringBuilder();
		builder.append("ClienteWs [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
