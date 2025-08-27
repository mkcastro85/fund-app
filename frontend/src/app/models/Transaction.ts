import { Fund } from "./Fund";

export interface Transaction {
  id: string;
  fund: Fund;
  amount: number;
  type: 'OPEN' | 'CLOSE';
  timestamp: string | null;
}