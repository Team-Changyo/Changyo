import React, { Dispatch, SetStateAction } from 'react';
import { ReactComponent as Shinhan } from 'assets/icons/banklogo/088.svg';
import { ReactComponent as Change } from 'assets/icons/account/change.svg';
import { IAccount } from 'types/account';
import { formatBankCode } from 'utils/common/formatBankCode';
import { AccountSelectorContainer } from './style';

function AccountSelectListItem({ account }: { account: IAccount }) {
	const formattedBankCode = formatBankCode(account.bankCode);
	return (
		<li>
			<div className="account">
				<Shinhan />
				{account.alias}{' '}
				<span>
					{formattedBankCode} {account.accountNumber}
				</span>
			</div>
			<div className="change">
				<Change />
			</div>
		</li>
	);
}

function AccountSelectList({
	accounts,
	setSelected,
}: {
	accounts: IAccount[];
	setSelected: Dispatch<SetStateAction<IAccount>>;
}) {
	return (
		<div>
			{accounts.map((el) => (
				<div key={el.key} onClick={() => setSelected(el)} role="presentation">
					<AccountSelectListItem account={el} />
				</div>
			))}
		</div>
	);
}

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
