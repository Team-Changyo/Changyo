import React from 'react';
import SmallSubText from 'components/atoms/common/SmallSubText';
import LargeMoneyText from 'components/atoms/common/LargeMoneyText';
import { AccountSummaryContainer } from './style';

/**
 * 계좌 총 개수, 토탈 금액 표기
 */
function AccountSummary({ accountCnt, totalMoney }: { accountCnt: number; totalMoney: number }) {
	return (
		<AccountSummaryContainer>
			<SmallSubText text={`총 ${accountCnt}개의 계좌`} />
			<LargeMoneyText money={totalMoney} />
		</AccountSummaryContainer>
	);
}

export default AccountSummary;
