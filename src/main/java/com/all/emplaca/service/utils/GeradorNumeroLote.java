package com.all.emplaca.service.utils;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class GeradorNumeroLote {
	/***
	 * O numero de lote de blanks gerado pelo emplaca e retornado ao Fabricante no
	 * serviço de solicitação de lotes de blanks deve serguir o formato com
	 * numero do fabricante normalizado com tamanho de 5 posições + 
	 * UUID de 32 posições.
	 *  
	 * 
	 * No caso de uma eventual colisão de identificador, uma exceção é gerada, pois
	 * é um Unique Key.
	 */
	 
	 public static String gerarNumeroLotePara(Long idFabricante) {
		 
		 String fabricante = StringUtils.leftPad(Long.toString(idFabricante), 5, '0');
		 String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();				 
		 
		 StringBuilder numeroLote = new StringBuilder();
		 numeroLote.append(fabricante).append(uuid);
		 
		 return numeroLote.toString();
		 
	 }

}
