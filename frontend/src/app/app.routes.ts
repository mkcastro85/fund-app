// src/app/app.routes.ts
import { Routes } from '@angular/router';
import { FundComponent } from './components/fund/fund.component';

export const routes: Routes = [
  { path: 'funds', component: FundComponent },
  { path: '', redirectTo: 'funds', pathMatch: 'full' }
];
