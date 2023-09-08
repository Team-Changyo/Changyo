import React, { Dispatch, SetStateAction } from 'react';
import { IAccount } from 'types/account';
import { AccountSelectorContainer } from './style';
import AccountSelectListItem from '../AccountSelectListItem';
import AccountSelectList from '../AccountSelectList';

interface IAccountSelectorProps {
	accounts: IAccount[];
	selected: IAccount;
	setSelected: Dispatch<SetStateAction<IAccount>>;
}

function AccountSelector({ accounts, selected, setSelected }: IAccountSelectorProps) {
	return (
		<AccountSelectorContainer>
			<AccountSelectListItem account={selected} />
			{/* TODO(0907) : onclick하면 계좌 선택하는 모달 만들기 */}
			{false ? <AccountSelectList accounts={accounts} setSelected={setSelected} /> : <div />}
		</AccountSelectorContainer>
	);
}

export default AccountSelector;
