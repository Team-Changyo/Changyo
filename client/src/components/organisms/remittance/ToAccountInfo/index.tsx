import React from 'react';
import AccountSelectListItem from 'components/organisms/account/AccountSelectListItem';
import { IAccount, IStoreAccount } from 'types/account';
import { ToAccountInfoContainer } from './style';

function ToAccountInfo({ storeAccount }: { storeAccount: IStoreAccount | undefined }) {
	const account: IAccount = {
		accountId: 0,
		balance: 0,
		mainAccount: true,
		accountNumber: storeAccount?.accountNumber ?? '',
		bankCode: storeAccount?.bankCode ?? '',
		title: storeAccount?.memberName ?? '',
	};
	return (
		<ToAccountInfoContainer>
			<AccountSelectListItem account={account} onlyView />
			<p>
				예금주 <span className="account-holder-name">{account.title}</span>
			</p>
		</ToAccountInfoContainer>
	);
}

export default ToAccountInfo;
