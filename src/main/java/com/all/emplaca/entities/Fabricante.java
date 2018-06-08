package com.all.emplaca.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import com.all.emplaca.utils.Mensagens;

@Entity
// @Audited
public class Fabricante extends ClienteWs {

	private static final long serialVersionUID = -8308907427873657183L;
	
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

	// TODO Incluir validação de telefone
	@Column
	private String telefone;

	@Column(name = "numero_portaria")
	private String numeroPortaria;

	// TODO Confirmar tipo LocalDate para campos de entidade

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
	
	@OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL)
	private List<Lote> lotes;
	
	@Column
	private Long quotaDeBlanks;

/*	 Não usar sancao no ToString 
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	// @Fetch(FetchMode.SUBSELECT)
	private List<Sancao> sancoes;*/

	public Fabricante() {
/*		if (sancoes == null) {
			sancoes = new ArrayList<Sancao>();
		}*/
	}
	
	public Fabricante(String nome, String cnpj, Boolean inativa, Long quotaDeBlanks) {
		super(nome);
		this.cnpj = cnpj;
		this.inativa = inativa;
		this.quotaDeBlanks = quotaDeBlanks;
	}
	
/* TODO Os impedimentos devem estar na camada de servico não na camada de entidade migrar
	public boolean estaComAcessoImpedido() {
		return impedidaPorAlvara() || impedidoPeloNumeroDoContrato()
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
	}*/

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNumeroPortaria() {
		return numeroPortaria;
	}

	public void setNumeroPortaria(String numeroPortaria) {
		this.numeroPortaria = numeroPortaria;
	}

	public LocalDate getDataLicenciamento() {
		return dataLicenciamento;
	}

	public void setDataLicenciamento(LocalDate dataLicenciamento) {
		this.dataLicenciamento = dataLicenciamento;
	}

	public LocalDate getValidadeAlvara() {
		return validadeAlvara;
	}

	public void setValidadeAlvara(LocalDate validadeAlvara) {
		this.validadeAlvara = validadeAlvara;
	}

	public LocalDate getValidadePortaria() {
		return validadePortaria;
	}

	public void setValidadePortaria(LocalDate validadePortaria) {
		this.validadePortaria = validadePortaria;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public LocalDate getValidadeContrato() {
		return validadeContrato;
	}

	public void setValidadeContrato(LocalDate validadeContrato) {
		this.validadeContrato = validadeContrato;
	}

	public LocalDate getDataPortaria() {
		return dataPortaria;
	}

	public void setDataPortaria(LocalDate dataPortaria) {
		this.dataPortaria = dataPortaria;
	}

	public Boolean getInativa() {
		return inativa;
	}

	public void setInativa(Boolean inativa) {
		this.inativa = inativa;
	}

	public String getMotivoInativacao() {
		return motivoInativacao;
	}

	public void setMotivoInativacao(String motivoInativacao) {
		this.motivoInativacao = motivoInativacao;
	}

	public List<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}
	
	

	public Long getQuotaDeBlanks() {
		return quotaDeBlanks;
	}

	public void setQuotaDeBlanks(Long quotaDeBlanks) {
		this.quotaDeBlanks = quotaDeBlanks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((dataLicenciamento == null) ? 0 : dataLicenciamento.hashCode());
		result = prime * result + ((dataPortaria == null) ? 0 : dataPortaria.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((inativa == null) ? 0 : inativa.hashCode());
		result = prime * result + ((motivoInativacao == null) ? 0 : motivoInativacao.hashCode());
		result = prime * result + ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result + ((numeroContrato == null) ? 0 : numeroContrato.hashCode());
		result = prime * result + ((numeroPortaria == null) ? 0 : numeroPortaria.hashCode());
		result = prime * result + ((quotaDeBlanks == null) ? 0 : quotaDeBlanks.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((validadeAlvara == null) ? 0 : validadeAlvara.hashCode());
		result = prime * result + ((validadeContrato == null) ? 0 : validadeContrato.hashCode());
		result = prime * result + ((validadePortaria == null) ? 0 : validadePortaria.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fabricante other = (Fabricante) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (dataLicenciamento == null) {
			if (other.dataLicenciamento != null)
				return false;
		} else if (!dataLicenciamento.equals(other.dataLicenciamento))
			return false;
		if (dataPortaria == null) {
			if (other.dataPortaria != null)
				return false;
		} else if (!dataPortaria.equals(other.dataPortaria))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (inativa == null) {
			if (other.inativa != null)
				return false;
		} else if (!inativa.equals(other.inativa))
			return false;
		if (motivoInativacao == null) {
			if (other.motivoInativacao != null)
				return false;
		} else if (!motivoInativacao.equals(other.motivoInativacao))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (numeroContrato == null) {
			if (other.numeroContrato != null)
				return false;
		} else if (!numeroContrato.equals(other.numeroContrato))
			return false;
		if (numeroPortaria == null) {
			if (other.numeroPortaria != null)
				return false;
		} else if (!numeroPortaria.equals(other.numeroPortaria))
			return false;
		if (quotaDeBlanks == null) {
			if (other.quotaDeBlanks != null)
				return false;
		} else if (!quotaDeBlanks.equals(other.quotaDeBlanks))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (validadeAlvara == null) {
			if (other.validadeAlvara != null)
				return false;
		} else if (!validadeAlvara.equals(other.validadeAlvara))
			return false;
		if (validadeContrato == null) {
			if (other.validadeContrato != null)
				return false;
		} else if (!validadeContrato.equals(other.validadeContrato))
			return false;
		if (validadePortaria == null) {
			if (other.validadePortaria != null)
				return false;
		} else if (!validadePortaria.equals(other.validadePortaria))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fabricante [nomeFantasia=");
		builder.append(nomeFantasia);
		builder.append(", cnpj=");
		builder.append(cnpj);
		builder.append(", endereco=");
		builder.append(endereco);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append(", numeroPortaria=");
		builder.append(numeroPortaria);
		builder.append(", dataLicenciamento=");
		builder.append(dataLicenciamento);
		builder.append(", validadeAlvara=");
		builder.append(validadeAlvara);
		builder.append(", validadePortaria=");
		builder.append(validadePortaria);
		builder.append(", numeroContrato=");
		builder.append(numeroContrato);
		builder.append(", validadeContrato=");
		builder.append(validadeContrato);
		builder.append(", dataPortaria=");
		builder.append(dataPortaria);
		builder.append(", inativa=");
		builder.append(inativa);
		builder.append(", motivoInativacao=");
		builder.append(motivoInativacao);
		builder.append(", quotaDeBlanks=");
		builder.append(quotaDeBlanks);
		builder.append("]");
		return builder.toString();
	}

		
	

/*	private boolean impedidaPorSancao() {
		for (Sancao sancao : sancoes) {
			if (sancao.impedeAcesso()) {
				return true;

			}
		}
		return false;
	}*/
	
	

}
