import { RemitApiBody, RemitDepositApiBody, ReturnDepositApiBody } from 'types/api';
import instance from './instance';

// TODO 간편 송금
export const remitApi = async (body: RemitApiBody) => {
	const response = await instance.post('/trade/simple', body);
	return response;
};

// 보증금 송금
export const remitDepositApi = async (body: RemitDepositApiBody) => {
	const response = await instance.post('/trade', body);
	return response;
};

// 보증금 반환
export const returnDepositApi = async (body: ReturnDepositApiBody) => {
	const response = await instance.post('/trade/deposit', body);
	return response;
};

// 보증금 송금 정보 조회
export const findDepositRemitInfoApi = async (qrCodeId: string) => {
	const response = await instance.get(`/transfer?qrCodeId=${qrCodeId}`);
	return response;
};

// 간편 송금 정보 조회
export const findNormalRemitInfoApi = async (simpleQrCodeId: string) => {
	const response = await instance.get(`/transfer/simple?qrCodeId=${simpleQrCodeId}`);
	return response;
};
