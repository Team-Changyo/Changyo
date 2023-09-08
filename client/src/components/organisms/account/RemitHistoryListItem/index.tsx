import React from 'react';
import { ReactComponent as Shinhan } from 'assets/icons/account/coin.svg';
import { IHistory } from 'types/account';
import { formatMoney } from 'utils/common/formatMoney';
import { RemitHistoryListItemContainer } from './style';

interface RemitHistoryListItemProps {
	history: IHistory;
}

function RemitHistoryListItem({ history }: RemitHistoryListItemProps) {
	return (
		<RemitHistoryListItemContainer>
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
		</RemitHistoryListItemContainer>
	);
}

export default RemitHistoryListItem;
