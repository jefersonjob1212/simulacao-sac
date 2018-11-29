package br.com.db1.financiamento.service;

import java.util.List;

import br.com.db1.financiamento.entity.Financiamento;
import br.com.db1.financiamento.entity.Parcela;

public interface ParcelaService {

	List<Parcela> getParcelasByFinanciamento(Financiamento financiamento);
}
