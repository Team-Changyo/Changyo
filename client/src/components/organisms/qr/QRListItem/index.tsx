import React from 'react';
import { ReactComponent as QR } from 'assets/icons/qr/qr-default-icon.svg';
import { formatMoney } from 'utils/common/formatMoney';
import { formatBankCode } from 'utils/common/formatBankCode';
import { useNavigate } from 'react-router-dom';
import { QRListItemContainer } from './style';

interface IQRListItemProps {
	title: string;
	bankCode: string;
	accountNumber: string;
	moneyUnit: number;
}

function QRListItem({ title, bankCode, accountNumber, moneyUnit }: IQRListItemProps) {
	const bankName = formatBankCode(bankCode);
	const formattedMoneyUnit = formatMoney(moneyUnit);
	const navigate = useNavigate();

	return (
		<QRListItemContainer onClick={() => navigate('/qr/deposit/1')}>
			<div className="qr-logo">
				<QR />
			</div>
			<div className="qr-info">
				<div className="title">
					<span>{title}</span> 건
				</div>
				<div className="account">
					받을계좌{' '}
					<span>
						{bankName} {accountNumber}
					</span>
				</div>
				<div className="money-unit">
					송금단위 <span>{formattedMoneyUnit}</span> 원
				</div>
			</div>
			<div className="detail-btn">
				<button type="button">상세</button>
			</div>
		</QRListItemContainer>
	);
}

export default QRListItem;
