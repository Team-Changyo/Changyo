import React from 'react';
import { ReactComponent as Shinhan } from 'assets/icons/banklogo/088.svg';
import { formatMoney } from 'utils/common/formatMoney';
import { useNavigate } from 'react-router-dom';
import { AccountListItemContainer } from './style';

interface AccountListItemProps {
	isMainAccount: boolean;
}

function AccountListItem({ isMainAccount }: AccountListItemProps) {
	const navigate = useNavigate();
	const balance = formatMoney(20000);

	return (
		<AccountListItemContainer onClick={() => navigate('/account/1')}>
			<div className="bank-logo">
				<Shinhan />
			</div>
			<div className="account-info">
				<div className="alias">항상 가난한 내 신한 {isMainAccount ? '👑' : ''}</div>
				<div className="balance">{balance} 원</div>
			</div>
			<div className="detail-btn">
				<button type="button">상세</button>
			</div>
		</AccountListItemContainer>
	);
}

export default AccountListItem;
