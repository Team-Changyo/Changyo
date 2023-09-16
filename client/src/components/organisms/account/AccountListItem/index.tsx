import React from 'react';
import { ReactComponent as Shinhan } from 'assets/icons/banklogo/088.svg';
import { formatMoney } from 'utils/common/formatMoney';
import { useNavigate } from 'react-router-dom';
import { IAccount } from 'types/account';
import { formatBankCode } from 'utils/common/formatBankCode';
import { AccountListItemContainer } from './style';

interface AccountListItemProps {
	account: IAccount;
}

function AccountListItem({ account }: AccountListItemProps) {
	const navigate = useNavigate();
	const balance = formatMoney(account?.balance);
	const bankName = formatBankCode(account?.bankCode);

	return (
		<AccountListItemContainer onClick={() => navigate(`/account/${account.accountId}`)}>
			<div className="bank-logo">
				<Shinhan />
			</div>
			<div className="account-info">
				<div>
					{account.title} {account?.mainAccount ? '👑' : ''}
				</div>
				<div className="alias">
					<span className="bankname">
						{bankName} {account.accountNumber}
					</span>
				</div>
				<div className="balance">{balance} 원</div>
			</div>
			<div className="detail-btn">
				<button type="button">상세</button>
			</div>
		</AccountListItemContainer>
	);
}

export default AccountListItem;
