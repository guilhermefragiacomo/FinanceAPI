export type TransactionType = 1 | 0

export interface Transaction {
    id : number;
	description : string;
	value : number;
	type : TransactionType;
	category : number;
	date : string;
}