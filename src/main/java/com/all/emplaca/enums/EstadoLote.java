package com.all.emplaca.enums;

public enum EstadoLote {
	
	SOLICITADO("1", "Lote solicitado pelo fabricante"), 
	BLANKS_GERADOS("2", "Todos blanks do lote foram gerados"), 
	BLANKS_REPASSADOS("3", "Todos blanks do lote foram repassados ao fabricante"), 
	CANCELADO("4", "Lote cancelado");

	private String value;
	private String label;

	private EstadoLote(String value, String label) {
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
