// REQUEST

import { IReturnSettlement } from './deposit';

// - member
export interface LoginApiBody {
	loginId: string;
	password: string;
}

export interface JoinApiBody {
	loginId: string;
	password: string;
	name: string;
	phoneNumber: string;
	role: string;
}

export interface WithdrawalApiBody {
	loginId: string;
	password: string;
}

// - account
export interface RegisterAccountApiBody {
	bankCode: string;
	accountNumber: string;
	title: string;
	mainAccount: boolean;
}

export interface AuthRequestAccountApiBody {
	bankCode: string;
	accountNumber: string;
}

export interface CheckAuthAccountApiBody {
	authenticationNumber: string;
}
// - remit
export interface RemitApiBody {
	accountId: number;
	simpleQrCodeId: string;
}

export interface RemitDepositApiBody {
	accountId: number;
	qrCodeId: number;
}

export interface ReturnDepositApiBody {
	returnRequests: IReturnSettlement[];
}

// - qr
export interface CreateQRApiBody {
	accountNumber: string;
	amount: number;
}

export interface CreateDepositQRApiBody {
	accountId: number;
	amount: number;
	title: string;
}

// RESPONSE
