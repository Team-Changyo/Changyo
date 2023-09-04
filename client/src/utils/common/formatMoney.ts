/**
 * number 타입 금액 -> locale에 따른 단위로 변환 (원, 달러 등)
 * @param amount 얼마?
 * @param locale default ko-KR
 * @returns 변환된 금액
 */
export const formatMoney = (amount: number, locale: string = 'ko-KR'): string => {
	const formatter = new Intl.NumberFormat(locale, {
		currency: 'KRW',
		minimumFractionDigits: 0, // 소수점 이하 자리 수를 0으로 설정하여 원 단위로 표시
	});

	return formatter.format(amount);
};
