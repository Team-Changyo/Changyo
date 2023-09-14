import React from 'react';
import { IAccount } from 'types/account';
import { formatBankCode } from 'utils/common/formatBankCode';
import { ReactComponent as Shinhan } from 'assets/icons/banklogo/088.svg';
import { ReactComponent as Change } from 'assets/icons/account/change.svg';
import { AccountSelectListItemContainer } from './style';

function AccountSelectListItem({ account, onlyView }: { account: IAccount; onlyView?: boolean }) {
	const bankName = formatBankCode(account?.bankCode);

	return (
		<AccountSelectListItemContainer>
			<div className="account">
				<Shinhan />
				{account?.title}
				<span>
					{bankName} {account?.accountNumber}
				</span>
			</div>
			{onlyView ? (
				<div />
			) : (
				<div className="change">
					<Change />
				</div>
			)}
		</AccountSelectListItemContainer>
	);
}

export default AccountSelectListItem;
