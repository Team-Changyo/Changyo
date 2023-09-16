import { CreateDepositQRApiBody, CreateQRApiBody } from 'types/api';
import instance from './instance';

// 간편 송금 QR 생성
export const createNormalQRApi = async (body: CreateQRApiBody) => {
	const response = await instance.post('/qrcode/simple', body);
	return response;
};

// 보증금 QR 생성
export const createDepositQRApi = async (body: CreateDepositQRApiBody) => {
	const response = await instance.post('/qrcode', body);
	return response;
};

// TODO 보증금 QR 수정
export const updateQRApi = async (qrCodeId: number) => {
	const response = await instance.patch(`/qrcode/amount/${qrCodeId}`);
	return response;
};

// 보증금 QR 전체 조회
export const findAllQRApi = async () => {
	const response = await instance.get('/qrcode');
	return response;
};

// 보증금 QR 상세 조회
export const findQRApi = async (qrCodeId: string) => {
	const response = await instance.get(`/qrcode/${qrCodeId}`);
	return response;
};
