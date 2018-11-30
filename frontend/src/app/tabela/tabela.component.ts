import { Financiamento } from './../entities/financiamento.entity';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { SimulacaoService } from '../services/simulacao.service';

@Component({
  selector: 'app-tabela',
  templateUrl: './tabela.component.html',
  styleUrls: ['./tabela.component.css']
})
export class TabelaComponent implements OnInit {

  dataSourceTable: MatTableDataSource<any>;
  columnsToDisplay: string[] = ['numero', 'vencimento', 'valor'];
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private simulacaoService: SimulacaoService) { }

  ngOnInit() {
  }

  simular(financiamento: Financiamento) {
    //console.log(financiamento);
    this.simulacaoService.simular(financiamento).subscribe(lis => {

      console.log(lis);
      //this.dataSourceTable = new MatTableDataSource(lis);
      this.dataSourceTable = new MatTableDataSource(lis);
      this.dataSourceTable.sort = this.sort;
      this.dataSourceTable.paginator = this.paginator;
    });
  }
}
