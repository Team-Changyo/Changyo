import { RemitApiBody, RemitDepositApiBody, ReturnDepositApiBody } from 'types/api';
import instance from './instance';

// TODO 간편 송금
export const remitApi = async (body: RemitApiBody) => {
	const response = await instance.post('/trade', body);
	return response;
};

// 보증금 송금
export const remitDepositApi = async (body: RemitDepositApiBody) => {
	const response = await instance.post('/trade', body);
	return response;
};

// TODO 보증금 반환
export const returnDepositApi = async (body: ReturnDepositApiBody) => {
	const response = await instance.post('/trade', body);
	return response;
};

// 보증금 송금 정보 조회
export const findRemitInfoApi = async (qrCodeId: string) => {
	const response = await instance.get(`/transfer?qrCodeId=${qrCodeId}`);
	return response;
};
