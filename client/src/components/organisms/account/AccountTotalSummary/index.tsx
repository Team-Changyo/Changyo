import React from 'react';
import SmallSubText from 'components/atoms/common/SmallSubText';
import LargeMoneyText from 'components/atoms/common/LargeMoneyText';
import { AccountTotalSummaryContainer } from './style';

/**
 * 계좌 총 개수, 토탈 금액 표기
 */
function AccountTotalSummary({ accountCnt, totalMoney }: { accountCnt: number; totalMoney: number }) {
	return (
		<AccountTotalSummaryContainer>
			<SmallSubText text={`총 ${accountCnt}개의 계좌`} />
			<LargeMoneyText money={totalMoney} />
		</AccountTotalSummaryContainer>
	);
}

export default AccountTotalSummary;
