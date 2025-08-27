import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Fund } from '../models/Fund';
import { Transaction } from '../models/Transaction';

@Injectable({
  providedIn: 'root'
})
export class FundService {

  private apiUrl = 'http://localhost:8080/api/funds';

  constructor(private http: HttpClient) { }

  getFunds(): Observable<Fund[]> {
    return this.http.get<Fund[]>(this.apiUrl);
  }

  subscribe(clientId: string, fundId: string, notification: string): Observable<Transaction> {
    return this.http.put<Transaction>(`${this.apiUrl}/subscribe/${clientId}/${fundId}?notification=${notification}`, {});
  }

  cancel(clientId: string, fundId: string): Observable<Transaction> {
    return this.http.put<Transaction>(`${this.apiUrl}/cancel/${clientId}/${fundId}`, {});
  }

  getTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.apiUrl}/transactions`);
  }
}
