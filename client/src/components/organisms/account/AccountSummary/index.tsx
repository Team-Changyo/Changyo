import React from 'react';
import LargeMoneyText from 'components/atoms/common/LargeMoneyText';
import { formatBankCode } from 'utils/common/formatBankCode';
import { AccountSummaryContainer } from './style';

interface IAccountSummaryProps {
	bankCode: string;
	accountNumber: string;
	totalMoney: number;
}
/**
 * 계좌 총 개수, 토탈 금액 표기
 */
function AccountSummary({ bankCode, accountNumber, totalMoney }: IAccountSummaryProps) {
	const bankName = formatBankCode(bankCode);

	return (
		<AccountSummaryContainer>
			<p className="account-info">
				{bankName} <span>{accountNumber}</span>
			</p>
			<LargeMoneyText money={totalMoney} />
		</AccountSummaryContainer>
	);
}

export default AccountSummary;
