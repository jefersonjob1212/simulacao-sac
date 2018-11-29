package br.com.db1.financiamento.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.db1.financiamento.entity.Financiamento;
import br.com.db1.financiamento.entity.Parcela;
import br.com.db1.financiamento.service.ParcelaService;

@Service
public class ParcelaServiceImpl implements ParcelaService {

	@Override
	public List<Parcela> getParcelasByFinanciamento(Financiamento financiamento) {
		// TODO Auto-generated method stub
		BigDecimal valorFinanciamento = new BigDecimal(0);
		valorFinanciamento = financiamento
								.getValorImovel()
								.multiply(financiamento.getPercEntrada())
								.divide(new BigDecimal(100));
		valorFinanciamento = financiamento.getValorImovel().subtract(valorFinanciamento);
		financiamento.setValor(valorFinanciamento);
		
		List<Parcela> lista = new ArrayList<Parcela>();
		
		/*
		 * Aqui criamos uma variável para salvar o valor anterior
		 */
		Parcela parcelaAnterior = new Parcela();
		
		BigDecimal amortizacao = new BigDecimal(0);
		BigDecimal valorParcela = new BigDecimal(0);
		BigDecimal juros = new BigDecimal(0);
		BigDecimal jurosMensal = new BigDecimal(0);
		BigDecimal parcelas = new BigDecimal(financiamento.getQtdParcelas());
		Date dataParcela = new Date();
		
		/*
		 * Aqui calculamos a amortização
		 */
		amortizacao = financiamento.getValor().divide(parcelas, 2, RoundingMode.HALF_UP);
		
		jurosMensal = new BigDecimal(Math.pow(
					new BigDecimal(financiamento.getJuros().add(new BigDecimal(1)).doubleValue()).doubleValue(), 
					new BigDecimal(1/12).doubleValue()));
		/*
		 * Aqui calculamos os juros do saldo devedor do financiamento
		 */
		juros = jurosMensal
				.multiply(financiamento.getValor())
				.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
		
		/*
		 * Fazemos um for para gerar o parcelamento, 
		 * começando da parcela 1 até o total informado pelo usuário
		 */
		for(int i = 1;i <= parcelas.intValue(); i++){
			Parcela parcelaAtual = new Parcela();
			parcelaAtual.setNumero(i);
			/*
			 * Se não for a primeira parcela, calcula o saldo devedor
			 * para ver o valor da próxima parcela.
			 */
			if(i > 1) {
				/*
				 * Aqui calculamos o saldo devedor do financiamento
				 */
				valorFinanciamento = valorFinanciamento.subtract(parcelaAnterior.getValor());
				
				/*
				 * Aqui calculamos os juros do saldo devedor do financiamento
				 */
				juros = jurosMensal
						.multiply(valorFinanciamento)
						.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
				
				/*
				 * Aqui definimos o vencimento
				 */
				dataParcela = parcelaAnterior.getVencimento();
				Calendar data = Calendar.getInstance();
				data.setTime(dataParcela);
				data.add(Calendar.DAY_OF_MONTH, 30);
				dataParcela = data.getTime();
			}
			/*
			 * Aqui somamos o valor da amortização aos juros
			 */
			valorParcela = amortizacao.add(juros);
			parcelaAtual.setValor(valorParcela);
			parcelaAtual.setVencimento(dataParcela);
			/*
			 * Aqui adicionamos à lista de parcelas
			 */
			lista.add(parcelaAtual);
			/*
			 * Aqui definimos a parcela anterior
			 */
			parcelaAnterior = parcelaAtual;
		}
		return lista;
	}

}
