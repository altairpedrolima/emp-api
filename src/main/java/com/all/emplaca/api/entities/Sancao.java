package com.all.emplaca.api.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.Id;

import com.all.emplaca.api.enums.MotivoSancao;
import com.all.emplaca.api.enums.TipoSancao;

@Entity
@Audited
@SequenceGenerator(allocationSize = 1, initialValue = 1000, name = "sancao_id_seq", sequenceName = "sancao_id_seq")
public class Sancao extends EmplacaEntity {

	private static final long serialVersionUID = -4281993809186872908L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sancao_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@Column
	// @Type(type = "br.gov.serpro.siscsv.util.usertype.LabeledValueEnumUserType",
	// parameters = { @Parameter(name = "enumClass", value =
	// "br.gov.serpro.siscsv.entity.enums.TipoSancao") })
	private TipoSancao tipoSancao;

	@Column
	// @Type(type = "br.gov.serpro.siscsv.util.usertype.LabeledValueEnumUserType",
	// parameters = { @Parameter(name = "enumClass", value =
	// "br.gov.serpro.siscsv.entity.enums.MotivoSancao") })
	private MotivoSancao motivo;

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

	public TipoSancao getTipo() {
		return tipoSancao;
	}

	public void setTipo(TipoSancao tipo) {
		this.tipoSancao = tipo;
	}

	public MotivoSancao getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoSancao motivo) {
		this.motivo = motivo;
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
			DateTimeFormatter formatador = 
					  DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
		return (tipoSancao == TipoSancao.SUSPENSAO30) || (tipoSancao == TipoSancao.SUSPENSAO60) || (tipoSancao == TipoSancao.SUSPENSAO90);
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
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
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
		if (motivo != other.motivo)
			return false;
		if (tipoSancao != other.tipoSancao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sancao [id=");
		builder.append(id);
		builder.append(", tipo=");
		builder.append(tipoSancao);
		builder.append(", motivo=");
		builder.append(motivo);
		builder.append(", dataInicioVigencia=");
		builder.append(dataInicioVigencia);
		builder.append(", cpfCadastrador=");
		builder.append(cpfCadastrador);
		builder.append("]");
		return builder.toString();
	}

}
