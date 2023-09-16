import { IShareData } from 'types/deposit';

export const share = async (data: IShareData) => {
	try {
		if (navigator.share) {
			await navigator.share(data);
			return true;
		}
		throw new Error('공유하기 기능을 지원하지 않는 브라우저 입니다.');
	} catch (error) {
		console.error('공유하기 실패', error);
		return false;
	}
};
