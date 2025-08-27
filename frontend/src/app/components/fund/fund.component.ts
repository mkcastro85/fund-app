import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FundService } from '../../services/fund.service';
import { Fund } from '../../models/Fund';
import { Transaction } from '../../models/Transaction';

@Component({
  selector: 'app-fund',
  imports: [CommonModule],
  templateUrl: './fund.component.html',
  styleUrl: './fund.component.scss'
})
export class FundComponent implements OnInit {
  funds: Fund[] = [];
  transactions: Transaction[] = [];
  clientId: string = "meyling";
  notification: string = "EMAIL"; 
  errorMessage: string | null = null; 

  constructor(private fundService: FundService) {}

  ngOnInit(): void {
    this.loadFunds();
    this.loadTransactions();
  }

  loadFunds() {
    this.fundService.getFunds().subscribe({
      next: (data) => this.funds = data,
      error: (err) => this.errorMessage = err.message || 'Error cargando fondos'
    });
  }

  loadTransactions() {
    this.fundService.getTransactions().subscribe({
      next: (data) => this.transactions = data,
      error: (err) => this.errorMessage = err.message || 'Error cargando transacciones'
    });
  }

  subscribe(fundId: string) {
    this.errorMessage = null;
    this.fundService.subscribe(this.clientId, fundId, this.notification).subscribe({
      next: () => this.loadTransactions(),
      error: (err) => {
        this.errorMessage = err.error?.message || 'Error al suscribirse al fondo';
      }
    });
  }

  cancel(fundId: string) {
    this.errorMessage = null;
    this.fundService.cancel(this.clientId, fundId).subscribe({
      next: () => this.loadTransactions(),
      error: (err) => {
        this.errorMessage = err.error?.message || 'Error al cancelar la suscripción';
      }
    });
  }
}
