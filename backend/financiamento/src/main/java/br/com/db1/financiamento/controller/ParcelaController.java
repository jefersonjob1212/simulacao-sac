package br.com.db1.financiamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.db1.financiamento.entity.Financiamento;
import br.com.db1.financiamento.entity.Parcela;
import br.com.db1.financiamento.service.impl.ParcelaServiceImpl;

@RestController
@CrossOrigin(value="*")
@RequestMapping(value="/parcela")
public class ParcelaController {
	
	@Autowired
	private ParcelaServiceImpl parcelaServiceImpl;
	
	@RequestMapping(value="/calcular", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Parcela> calcularParcelas(@RequestBody Financiamento financiamento) {
		return this.parcelaServiceImpl.getParcelasByFinanciamento(financiamento);
	}

}
