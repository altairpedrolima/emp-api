package com.all.emplaca.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.all.emplaca.enums.MotivoSancao;
import com.all.emplaca.enums.TipoSancao;

public class Sancao implements Serializable {
	private static final long serialVersionUID = -6453349004509598188L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// TODO Associar sanção com fabricante/estampador?

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_sancao", nullable = false)
	private TipoSancao tipoSancao;

	@Enumerated(EnumType.STRING)
	@Column(name = "motivo_sancao", nullable = false)
	private MotivoSancao motivoSancao;

	@Column(name = "data_inicio_vigencia")
	private LocalDate dataInicioVigencia;

	@Column(name = "cpf_cadastrador")
	private String cpfCadastrador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoSancao getTipoSancao() {
		return tipoSancao;
	}

	public void setTipoSancao(TipoSancao tipoSancao) {
		this.tipoSancao = tipoSancao;
	}

	public MotivoSancao getMotivoSancao() {
		return motivoSancao;
	}

	public void setMotivoSancao(MotivoSancao motivoSancao) {
		this.motivoSancao = motivoSancao;
	}

	public LocalDate getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public String getCpfCadastrador() {
		return cpfCadastrador;
	}

	public void setCpfCadastrador(String cpfCadastrador) {
		this.cpfCadastrador = cpfCadastrador;
	}

	public LocalDate getDataExpiracao() {
		LocalDate dataExpiracao = null;

		if (tipoSancao != null) {
			switch (tipoSancao) {
			case SUSPENSAO30:
				dataExpiracao = dataInicioVigencia.plusDays(30);
				break;
			case SUSPENSAO60:
				dataExpiracao = dataInicioVigencia.plusDays(60);
				break;
			case SUSPENSAO90:
				dataExpiracao = dataInicioVigencia.plusDays(90);
				break;
			default:
				break;
			}
		}

		return dataExpiracao;
	}

	public String getDataExpiracaoAsString() {
		LocalDate dataExpiracao = getDataExpiracao();
		if (dataExpiracao != null) {
			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return dataExpiracao.format(formatador);

		} else {
			return "Não se aplica";
		}
	}

	public boolean impedeAcesso() {
		if (tipoSancao == TipoSancao.CASSACAO) {
			return true;
		}
		LocalDate dataExpiracao = getDataExpiracao();
		if (dataExpiracao != null) {
			LocalDate dataAtual = LocalDate.now();
			if (dataAtual.isBefore(dataExpiracao)) {
				return true;
			}
		}
		return false;
	}

	public boolean isSuspensao() {
		return (tipoSancao == TipoSancao.SUSPENSAO30) || (tipoSancao == TipoSancao.SUSPENSAO60)
				|| (tipoSancao == TipoSancao.SUSPENSAO90);
	}

	public boolean isCassacao() {
		return tipoSancao == TipoSancao.CASSACAO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpfCadastrador == null) ? 0 : cpfCadastrador.hashCode());
		result = prime * result + ((dataInicioVigencia == null) ? 0 : dataInicioVigencia.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motivoSancao == null) ? 0 : motivoSancao.hashCode());
		result = prime * result + ((tipoSancao == null) ? 0 : tipoSancao.hashCode());
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
		Sancao other = (Sancao) obj;
		if (cpfCadastrador == null) {
			if (other.cpfCadastrador != null)
				return false;
		} else if (!cpfCadastrador.equals(other.cpfCadastrador))
			return false;
		if (dataInicioVigencia == null) {
			if (other.dataInicioVigencia != null)
				return false;
		} else if (!dataInicioVigencia.equals(other.dataInicioVigencia))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motivoSancao != other.motivoSancao)
			return false;
		if (tipoSancao != other.tipoSancao)
			return false;
		return true;
	}
	
}
