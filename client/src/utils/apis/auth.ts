import { JoinApiBody, LoginApiBody, WithdrawalApiBody } from 'types/api';
import instance from './instance';

// 로그인
export const loginApi = async (body: LoginApiBody) => {
	const response = await instance.post('/login', body);
	return response;
};

// 회원가입
export const joinApi = async (body: JoinApiBody) => {
	const response = await instance.post('/join', body);
	return response;
};

// 회원탈퇴
export const withdrawalApi = async (body: WithdrawalApiBody) => {
	const response = await instance.post('/withdrawal', body);
	return response;
};

// 회원 정보조회
export const findMemberInfo = async () => {
	const response = await instance.get('/info');
	return response;
};
