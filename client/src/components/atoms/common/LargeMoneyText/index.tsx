import React from 'react';
import { formatMoney } from 'utils/common/formatMoney';
import { LargeMoneyTextWrapper } from './style';

function LargeMoneyText({ money }: { money: number }) {
	const formattedMoney = formatMoney(money);
	return <LargeMoneyTextWrapper>{formattedMoney} 원</LargeMoneyTextWrapper>;
}

export default LargeMoneyText;
