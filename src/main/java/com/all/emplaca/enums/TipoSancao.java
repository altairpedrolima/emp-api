package com.all.emplaca.enums;

public enum TipoSancao {
	
	ADVERTENCIA("1", "Advertência"), 
	SUSPENSAO30("2", "Suspensão da licença por 30 dias"), 
	SUSPENSAO60("3", "Suspensão da licença por 60 dias"), 
	SUSPENSAO90("4", "Suspensão da licença por 90 dias"), 
	CASSACAO("5", "Cassação");

	private String value;
	private String label;

	private TipoSancao(String value, String label) {
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
	
	


}
