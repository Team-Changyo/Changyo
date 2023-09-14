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

// 계좌 상세조회
export const findAccountApi = async (accountId: number) => {
	const response = await instance.get(`/account/${accountId}`);
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
