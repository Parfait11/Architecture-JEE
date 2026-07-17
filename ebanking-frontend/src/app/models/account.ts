import { Customer } from './customer';

export interface BankAccount {
    type: string;
    id: string;
    balance: number;
    createdAt: Date;
    customerDTO: Customer;
    overDraft?: number;
    interestRate?: number;
}
