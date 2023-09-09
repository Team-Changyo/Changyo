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
	price: number;
	balance: number;
}
