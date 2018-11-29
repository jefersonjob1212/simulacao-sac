package br.com.db1.financiamento.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Parcela {
	
	private Integer numero;
	
	private Date vencimento;
	
	private BigDecimal valor;
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

}
