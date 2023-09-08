import React, { Dispatch, SetStateAction } from 'react';
import { IAccount } from 'types/account';
import AccountSelectListItem from '../AccountSelectListItem';

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

export default AccountSelectList;
