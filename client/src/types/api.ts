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
