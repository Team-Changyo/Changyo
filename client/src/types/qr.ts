export interface IQRCode {
	qrCodeId: number;
	title: string;
	accountNumber: string;
	bankCode: string;
	amount: number;
}

export interface INormalQRCode {
	accountNumber: string;
	amount: number;
	bankCode: string;
	base64QrCode: string;
	createdDate: string;
	memberName: string;
	simpleQrCodeId: number;
	url: string;
}
