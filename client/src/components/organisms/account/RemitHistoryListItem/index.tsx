import React from 'react';
import { ReactComponent as Shinhan } from 'assets/icons/account/coin.svg';
import { formatMoney } from 'utils/common/formatMoney';
import { ITradeHistory } from 'types/account';
import { RemitHistoryListItemContainer } from './style';

interface RemitHistoryListItemProps {
	history: ITradeHistory;
}

function RemitHistoryListItem({ history }: RemitHistoryListItemProps) {
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
				<span className="price">
					{history.status === 1 ? formatMoney(history.depositAmount) : `-${formatMoney(history.withdrawalAmount)}`}원
				</span>
				<span className="balance">{formatMoney(history.balance)}원</span>
			</div>
		</RemitHistoryListItemContainer>
	);
}

export default RemitHistoryListItem;
