export interface IAccount {
	accountId: number;
	accountNumber: string;
	balance: number;
	bankCode: string;
	mainAccount: boolean;
	title: string;
}

// 점주 계좌
export interface IStoreAccount {
	memberName: string;
	amount: number;
	bankCode: string;
	accountNumber: string;
}
// 점주 계좌 (보증금 송금 시 사용)
export interface IDepositStoreAccount extends IStoreAccount {
	qrCodeId: number;
	qrCodeTitle: string;
	productName: string;
}

// 점주 계좌 (보증금 송금 시 사용)
export interface INormalStoreAccount extends IStoreAccount {
	simpleQrCodeId: number;
}

export interface IDetailInfo {
	accountId: number;
	accountNumber: string;
	balance: number;
	bankCode: string;
	title: string;
	allTradeResponses: {
		[date: string]: ITradeHistory[];
	};
}
export interface ITradeHistory {
	tradeDate: string;
	tradeTime: string;
	content: string;
	balance: number;
	withdrawalAmount: number;
	depositAmount: number;
	status: number;
}
