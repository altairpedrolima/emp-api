package com.all.emplaca.api.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.springframework.util.StringUtils;

import com.all.emplaca.api.constants.Mensagens;

@Entity
@Audited
public class Empresa extends ClienteWs {

	private static final long serialVersionUID = -3625447090815510064L;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	@Column(unique = true)
	private String cnpj;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@Column(columnDefinition = "TEXT")
	@Email(message = Mensagens.EMAIL_INVALIDO)
	private String email;

	//TODO Incluir validação de telefone
	@Column
	private String telefone;

	@Column(name = "numero_portaria")
	private String numeroPortaria;

	@Column(name = "data_licenciamento")
	private LocalDate dataLicenciamento;

	@Column(name = "validade_alvara")
	private LocalDate validadeAlvara;

	@Column(name = "validade_portaria")
	private LocalDate validadePortaria;

	@Column(name = "numero_contrato")
	private String numeroContrato;

	@Column(name = "validade_contrato")
	private LocalDate validadeContrato;

	@Column(name = "data_portaria")
	private LocalDate dataPortaria;
	
	@Column
	private Boolean inativa;
	
	@Column(name = "motivo_inativacao")
	private String motivoInativacao;
	
	/* Não usar sancao no ToString */
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Sancao> sancoes;
	
	public Empresa() {
		if (sancoes == null) {
			sancoes = new ArrayList<Sancao>();
		}
	}
	
	public boolean estaComAcessoImpedido() {
		return impedidaPorAlvara() || impedidaPorSancao() || impedidoPeloNumeroDoContrato()
				|| impedidaPorValidadeDoContrato() || impedidaPorInativacao() || impedidaPorValidadeDaPortaria();
	}
	
	private boolean impedidaPorInativacao() {
		if (inativa != null && inativa == true) {
			return true;
		} else {
			return false;
		}
	}

	private boolean impedidaPorAlvara() {
		LocalDate hoje = LocalDate.now();
		LocalDate vldAlvara = validadeAlvara;
		return validadeAlvara != null && vldAlvara.isBefore(hoje);
	}

	private boolean impedidaPorValidadeDoContrato() {
		LocalDate hoje = LocalDate.now();
		LocalDate vldContrato = validadeContrato;
		return validadeContrato != null && vldContrato.isBefore(hoje);
	}

	private boolean impedidaPorValidadeDaPortaria() {
		LocalDate hoje = LocalDate.now();
		LocalDate vldPortaria = validadePortaria;
		return validadePortaria != null && vldPortaria.isBefore(hoje);
	}

	private boolean impedidoPeloNumeroDoContrato() {
		return StringUtils.isEmpty(numeroContrato);
	}

	private boolean impedidaPorSancao() {
		for (Sancao sancao : sancoes) {
			if (sancao.impedeAcesso()) {
				return true;

			}
		}
		return false;
	}

}
