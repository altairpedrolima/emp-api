package com.all.emplaca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {

	private static final long serialVersionUID = -6950950018889063785L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	String nome;

	@ManyToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "perfil_permissao", joinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "permissao_id", referencedColumnName = "id"))
	private List<Permissao> permissoes = new ArrayList<Permissao>();

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "usuario_perfil", joinColumns = { @JoinColumn(name = "id_perfil") }, inverseJoinColumns = {
			@JoinColumn(name = "id_usuario") })
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "perfil__perfil_permitido_cadastro_usuario", joinColumns = {
			@JoinColumn(name = "id_perfil") }, inverseJoinColumns = {
					@JoinColumn(name = "id_perfil_permitido_cadastro_usuario") })
	private List<Perfil> perfisPermitidosParaCadastroDeUsuario = new ArrayList<>();

	public Perfil() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Perfil> getPerfisPermitidosParaCadastroDeUsuario() {
		return perfisPermitidosParaCadastroDeUsuario;
	}

	public void setPerfisPermitidosParaCadastroDeUsuario(List<Perfil> perfisPermitidosParaCadastroDeUsuario) {
		this.perfisPermitidosParaCadastroDeUsuario = perfisPermitidosParaCadastroDeUsuario;
	}

	public boolean possuiPermissaoParaRecurso(String recurso) {

		for (Permissao permissao : permissoes) {
			if (permissao.getRecurso().getDescricao().equals(recurso)) {
				return true;
			}
		}

		return false;
	}

	public boolean possuiPermissaoParaRecursoOperacao(String recurso, String operacao) {

		for (Permissao permissao : permissoes) {
			if (permissao.getRecurso().getDescricao().equals(recurso)
					&& permissao.getOperacao().getDescricao().equals(operacao)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Perfil [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append("]");
		return builder.toString();
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
		Perfil other = (Perfil) obj;
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

}
