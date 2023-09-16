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
			{accounts.length ? (
				accounts.map((el) => (
					<div key={el.accountId} onClick={() => setSelected(el)} role="presentation">
						<AccountSelectListItem account={el} />
					</div>
				))
			) : (
				<div />
			)}
		</div>
	);
}

export default AccountSelectList;
