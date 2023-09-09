export interface IDepositHistory {
	key: number;
	title: string;
	remitDestination: string;
	moneyUnit: number;
	returnDateTime: string;
}

export interface ISettlement {
	key: number;
	title: string;
	moneyUnit: number;
	cntBeforeReturn: number;
}
