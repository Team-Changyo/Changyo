import React from 'react';
import { ReactComponent as Shinhan } from 'assets/icons/account/coin.svg';
import { IHistory } from 'types/account';
import { formatMoney } from 'utils/common/formatMoney';
import { HistoryListItemContainer } from './style';

interface HistoryListItemProps {
	history: IHistory;
}

function HistoryListItem({ history }: HistoryListItemProps) {
	return (
		<HistoryListItemContainer>
			<div className="history-logo">
				<Shinhan />
			</div>
			<div className="history-info">
				<span className="title">{history.title}</span>
				<span className="time">{history.time}</span>
			</div>
			<div className="history-money-info">
				<span className="price">{formatMoney(history.price)}원</span>
				<span className="balance">{formatMoney(history.balance)}원</span>
			</div>
		</HistoryListItemContainer>
	);
}

export default HistoryListItem;
