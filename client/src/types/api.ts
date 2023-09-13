// REQUEST

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
export interface RegisterAccountApi {
	bankCode: string;
	accountNumber: string;
	title: string;
	mainAccount: boolean;
}

// - remit
export interface RemitApiBody {}

export interface RemitDepositApiBody {
	accountId: number;
	withdrawalAccountNumber: string;
	qrCodeId: number;
	qrCodeTitle: string;
	depositAccountNumber: string;
	amount: number;
	content: string;
}

export interface ReturnDepositApiBody {}

// - qr
export interface CreateQRApiBody {
	accountId: number;
	amount: number;
	title: string;
}

export interface CreateDepositQRApiBody {
	accountId: number;
	amount: number;
}

// RESPONSE
