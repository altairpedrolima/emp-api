package com.all.emplaca.service.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.all.emplaca.exception.DataIntegrityException;

public class GeradorDeSerialDeBlanks {
	/***
	 * O numero de serial de blanks gerado pelo emplaca deve serguir o formato com
	 * AAMMFFSSSSSSSSS onde: AA = ano, MM = mes, FF = id do fabricante SSSSSSSSS =
	 * sequencia com 9 posições gerado a partir das ultimas posições de sequence em
	 * banco de dados
	 * 
	 * O numero serial deve ter tamanho fixo de 15 posições
	 * 
	 * No caso de uma eventual colisão de identificador, uma exceção é gerada, pois
	 * é este valor é um Unique Key.
	 * 
	 */

	@Autowired
	private static JdbcTemplate jdbcTemplate;

	public static String gerarNumeroSerial(Long idFabricante) {

		if (idFabricante > 99) {
			throw new DataIntegrityException("Não é possível gerar serial para fabricante com id maior que 99.");

		}

		String strAnoFull = Integer.toString(LocalDate.now().getYear());

		String strAno = strAnoFull.substring(strAnoFull.length() - 2);
		String strMes = Integer.toString(LocalDate.now().getMonthValue());

		String strFabricante = StringUtils.leftPad(Long.toString(idFabricante), 2, '0');

		String strSequencial = StringUtils.leftPad(Long.toString(getNextVal()), 9, '0');

		StringBuilder serial = new StringBuilder();
		serial.append(strAno).append(strMes).append(strFabricante).append(strSequencial);

		return serial.toString();

	}

	private static Long getNextVal() {
		Long nextVal = 0l;
		
		String qrysql = "select nextval('serial_blanks_seq');";

		List<Map<String,Object>> mapNextVal = jdbcTemplate.queryForList("select nextval('serial_blanks_seq');");

		// int mapNextVal = jdbcTemplate.update(qrysql);
				
				//queryForList("select nextval('serial_blanks_seq');");

		
		System.out.println(mapNextVal);

		// nextVal = jdbcTemplate.queryForObject("Select serial_blanks_seq.nextval()",
		// (rs, rowNum) -> new Long(rs.getInt("nextval")));

		return nextVal;

	}

}
