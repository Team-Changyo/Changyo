import React from 'react';
import AccountSelectListItem from 'components/organisms/account/AccountSelectListItem';
import { IAccount } from 'types/account';
import { ToAccountInfoContainer } from './style';

function ToAccountInfo() {
	const account: IAccount = {
		key: 1,
		alias: '항상 가난한 내 신한',
		accountNumber: '1234567895',
		bankCode: '088',
		accountHolder: '전인혁',
	};
	return (
		<ToAccountInfoContainer>
			<AccountSelectListItem account={account} onlyView />
			<p>
				예금주 <span className="account-holder-name">{account.accountHolder}</span>
			</p>
		</ToAccountInfoContainer>
	);
}

export default ToAccountInfo;
