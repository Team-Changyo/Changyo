export interface IDepositHistory {
	key: number;
	title: string;
	remitDestination: string;
	moneyUnit: number;
	returnDateTime: string;
}

export interface ISettlementGroup {
	key: number;
	title: string;
	moneyUnit: number;
	cntBeforeReturn: number;
}

export interface ISettlement {
	key: number;
	depositorName: string;
	dateTime: string;
	isReturned: boolean;
}
