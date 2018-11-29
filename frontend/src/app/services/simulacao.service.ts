import { Parcela } from './../entities/parcela.entity';
import { Financiamento } from '../entities/financiamento.entity';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SimulacaoService {

  private urlApi: String = 'http://localhost:8000/parcela/calcular';
  private httpOptions = {};
  constructor(private http: HttpClient) { 
    this.httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=UTF-8'})
    }
  }

  simular(financiamento: Financiamento): Observable<any> {
    return this.http.post<any>(`${this.urlApi}`, JSON.stringify(financiamento), this.httpOptions)
    .pipe(map(res => res));
  }
}
