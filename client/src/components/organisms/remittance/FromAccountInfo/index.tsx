import React from 'react';
import AccountSelectListItem from 'components/organisms/account/AccountSelectListItem';
import { IAccount } from 'types/account';
import { formatMoney } from 'utils/common/formatMoney';
import { FromAccountInfoContainer } from './style';

function FromAccountInfo({ account }: { account: IAccount }) {
	return (
		<FromAccountInfoContainer>
			{account ? <AccountSelectListItem account={account} /> : <div />}
			<p className="balance">잔액 {formatMoney(account?.balance)}원</p>
		</FromAccountInfoContainer>
	);
}

export default FromAccountInfo;
