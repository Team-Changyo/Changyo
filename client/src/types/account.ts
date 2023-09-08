export interface IAccount {
	key: number;
	alias: string;
	accountNumber: string;
	bankCode: string;
	accountHolder: string;
}

export interface IHistory {
	key: number;
	title: string;
	time: string;
	type: string;
	price: number;
	balance: number;
}
