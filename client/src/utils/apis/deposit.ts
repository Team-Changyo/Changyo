import instance from './instance';

// 보증금 송금내역 전체조회
export const findAllWaitRemitHistoryApi = async () => {
	const response = await instance.get('/trade/withdrawal/wait ');
	return response;
};

// 보증금 송금내역 전체조회
export const findAllDoneRemitHistoryApi = async () => {
	const response = await instance.get(`/trade/withdrawal/done`);
	return response;
};

// 보증금 정산관리 전체조회
export const findAllSettlementGroupApi = async () => {
	const response = await instance.get('/trade/deposit');
	return response;
};

// 보증금 정산관리 상세조회
export const findAllSettlementApi = async (qrCodeId: string) => {
	const response = await instance.get(`/trade/deposit/detail?qrCodeId=${qrCodeId}`);
	return response;
};
