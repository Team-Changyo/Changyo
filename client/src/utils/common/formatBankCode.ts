import { BANKNAMES_BY_BANKCODES } from 'constants/bankCode';

/**
 * number 타입 금액 -> locale에 따른 단위로 변환 (원, 달러 등)
 * @param amount 얼마?
 * @param locale default ko-KR
 * @returns 변환된 금액
 */
export const formatBankCode = (bankCode: string): string => {
	return BANKNAMES_BY_BANKCODES.find((el) => el.key === bankCode)?.value ?? '';
};
