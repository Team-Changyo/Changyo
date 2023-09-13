export interface IDepositHistory {
	key: number;
	title: string;
	remitDestination: string;
	moneyUnit: number;
	returnDateTime: string;
}

export interface ISettlementGroup {
	qrCodeId: number;
	qrCodeTitle: string;
	amount: number;
	remainTotal: number;
	remainCount: number;
}

export interface ISettlement {
	tradeId: number;
	status: 'WAIT' | 'DONE';
	memberName: string;
	tradeDate: string;
}
