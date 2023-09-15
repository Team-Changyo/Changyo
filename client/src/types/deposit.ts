export interface IDepositHistory {
	tradeId: number;
	qrCodeTitle: string;
	memberName: string;
	amount: number;
	tradeDate: string;
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

export interface IReturnSettlement {
	tradeId: number;
	amount: number;
	fee: number;
	reason: string;
	description: string;
}
