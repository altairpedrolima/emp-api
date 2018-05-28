package com.all.emplaca.response;

import java.util.Collections;
import java.util.List;

public class Response<T> {
	private T data;
	private List<String> erros;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<String> getErros() {
		if (this.erros == null) {
			this.erros = Collections.emptyList();			
		}
		return erros;
	}
	public void setErros(List<String> erros) {
		this.erros = erros;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((erros == null) ? 0 : erros.hashCode());
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
		Response<?> other = (Response<?>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (erros == null) {
			if (other.erros != null)
				return false;
		} else if (!erros.equals(other.erros))
			return false;
		return true;
	}
	
}
