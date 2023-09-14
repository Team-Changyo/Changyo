import { AuthRequestAccountApiBody, CheckAuthAccountApiBody, RegisterAccountApiBody } from 'types/api';
import instance from './instance';

// 계좌 등록
export const registerAccountApi = async (body: RegisterAccountApiBody) => {
	const response = await instance.post('/account', body);
	return response;
};

// 계좌 전체조회
export const findAllAccountApi = async () => {
	const response = await instance.get('/account');
	return response;
};

// 계좌 상세조회 (전체)
export const findAccountAllApi = async (accountId: string) => {
	const response = await instance.get(`/account/${accountId}`);
	return response;
};

// 계좌 상세조회 (입금)
export const findAccountInApi = async (accountId: string) => {
	const response = await instance.get(`/account/deposit/${accountId}`);
	return response;
};

// 계좌 상세조회 (출금)
export const findAccountOutApi = async (accountId: string) => {
	const response = await instance.get(`/account/withdrawal/${accountId}`);
	return response;
};

// 1원 이체 요청
export const authRequestAccountApi = async (body: AuthRequestAccountApiBody) => {
	const response = await instance.post('/authentication', body);
	return response;
};

// 1원 이체 확인
export const checkAuthAccountApi = async (body: CheckAuthAccountApiBody) => {
	const response = await instance.post('/authentication/check', body);
	return response;
};
