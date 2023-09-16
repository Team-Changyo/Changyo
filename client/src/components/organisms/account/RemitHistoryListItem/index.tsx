import React from 'react';
import { ReactComponent as Shinhan } from 'assets/icons/account/coin.svg';
import { formatMoney } from 'utils/common/formatMoney';
import { ITradeHistory } from 'types/account';
import { RemitHistoryListItemContainer } from './style';

interface RemitHistoryListItemProps {
	history: ITradeHistory;
}

function RemitHistoryListItem({ history }: RemitHistoryListItemProps) {
	const formattedMoney = formatMoney(history.balance);

	return (
		<RemitHistoryListItemContainer>
			<div className="history-logo">
				<Shinhan />
			</div>
			<div className="history-info">
				<span className="title">{history.content}</span>
				<span className="time">{history.tradeTime}</span>
			</div>
			<div className="history-money-info">
				{history.status === 1 ? (
					<span className="price primary">{`${formatMoney(history.depositAmount)}원`}</span>
				) : (
					<span className="price">-{`${formatMoney(history.withdrawalAmount)}원`}</span>
				)}
				<span className="balance">{formattedMoney}원</span>
			</div>
		</RemitHistoryListItemContainer>
	);
}

export default RemitHistoryListItem;
