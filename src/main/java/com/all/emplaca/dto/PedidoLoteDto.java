package com.all.emplaca.dto;

public class PedidoLoteDto {
	private Long fabricanteId;
	private Long quantidade;
	public Long getFabricanteId() {
		return fabricanteId;
	}
	public void setFabricanteId(Long fabricanteId) {
		this.fabricanteId = fabricanteId;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fabricanteId == null) ? 0 : fabricanteId.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
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
		PedidoLoteDto other = (PedidoLoteDto) obj;
		if (fabricanteId == null) {
			if (other.fabricanteId != null)
				return false;
		} else if (!fabricanteId.equals(other.fabricanteId))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("pedidoLoteDto [fabricanteId=");
		builder.append(fabricanteId);
		builder.append(", quantidade=");
		builder.append(quantidade);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
