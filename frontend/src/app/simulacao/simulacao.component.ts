import { Financiamento } from '../entities/financiamento.entity';
import { Component, OnInit, ViewChild } from '@angular/core';
import { TabelaComponent } from '../tabela/tabela.component';

@Component({
  providers: [TabelaComponent],
  selector: 'app-simulacao',
  templateUrl: './simulacao.component.html',
  styleUrls: ['./simulacao.component.css']
})
export class SimulacaoComponent implements OnInit {
  
  financiamento: Financiamento = new Financiamento();
  showTable: boolean = false;
  
  constructor(private tabelaComponent: TabelaComponent) { }

  ngOnInit() {
  }

  simular() {
    this.tabelaComponent.simular(this.financiamento);
    this.showTable = true;
  }
}
