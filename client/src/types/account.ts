export interface IAccount {
	accountId: number;
	accountNumber: string;
	balance: number;
	bankCode: string;
	mainAccount: boolean;
}

export interface IHistory {
	key: number;
	title: string;
	time: string;
	price: number;
	balance: number;
}
