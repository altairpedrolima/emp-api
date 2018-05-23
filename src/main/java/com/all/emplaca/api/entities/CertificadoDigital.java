package com.all.emplaca.api.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="certificado_digital")
@Audited
@SequenceGenerator(allocationSize = 1, initialValue = 1000, name = "certificado_digital_id_seq", sequenceName = "certificado_digital_entidade_id_seq")
public class CertificadoDigital implements Serializable { 
	private static final long serialVersionUID = 5637480585028152040L;

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certificado_digital_id_seq")
    private Long id;

    
    @Column(precision = 64, scale = 0)
    private BigInteger serial;
    
    @Column(columnDefinition = "TEXT")
    private String commonName;
    
    @Column(columnDefinition = "TEXT")
    private String commonNameEmissor;

    public void setSerialAsHex(String serialAsHexString) {
        if (serialAsHexString != null && serialAsHexString != "") {
            try {
                serial = new BigInteger(serialAsHexString, 16);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Serial inválido (deve estar em formato hexadecimal e sem espaços)");
            }
        }
    }

    public String getSerialAsHex() {
        if (serial != null) {
            return serial.toString(16).toUpperCase();
        }
        return null;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getSerial() {
		return serial;
	}

	public void setSerial(BigInteger serial) {
		this.serial = serial;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getCommonNameEmissor() {
		return commonNameEmissor;
	}

	public void setCommonNameEmissor(String commonNameEmissor) {
		this.commonNameEmissor = commonNameEmissor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commonName == null) ? 0 : commonName.hashCode());
		result = prime * result + ((commonNameEmissor == null) ? 0 : commonNameEmissor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
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
		CertificadoDigital other = (CertificadoDigital) obj;
		if (commonName == null) {
			if (other.commonName != null)
				return false;
		} else if (!commonName.equals(other.commonName))
			return false;
		if (commonNameEmissor == null) {
			if (other.commonNameEmissor != null)
				return false;
		} else if (!commonNameEmissor.equals(other.commonNameEmissor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CertificadoDigital [id=");
		builder.append(id);
		builder.append(", serial=");
		builder.append(serial);
		builder.append(", commonName=");
		builder.append(commonName);
		builder.append(", commonNameEmissor=");
		builder.append(commonNameEmissor);
		builder.append("]");
		return builder.toString();
	}
	
    
    
	
	

}
