package com.all.emplaca.enums;

public enum MotivoSancao {
	INFORMACOES_FALSAS("1", "Informações não verdadeiras"),
	INSPECAO_FORA_LOCALIDADE("2", "Realizar inspeção fora da empresa"),
	CLIENTE_SEM_DOCUMENTO("3", "Deixar de exigir do cliente documentação obrigatória"),
	SUSPENSAO_NO_SISTEMA_LEGADO("4", "Suspensão no sistema legado");
	
	private String value;
	private String label;

	private MotivoSancao(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public static MotivoSancao parse(String value) {
		for (MotivoSancao tipo : MotivoSancao.values()) {
			if (tipo.getValue().equals(value)) {
				return tipo;
			}
		}
		return null;
	}
	
}
