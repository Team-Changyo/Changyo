import React from 'react';
import { ReactComponent as Coin } from 'assets/icons/account/coin.svg';
import { ReactComponent as Right } from 'assets/icons/navigation/right.svg';
import { IDepositHistory } from 'types/deposit';
import { HistoryListItemContainer } from './style';

interface IDepositHistoryItemProps {
	history: IDepositHistory;
	isDone: boolean;
}
function DepositHistoryItem({ history, isDone }: IDepositHistoryItemProps) {
	return (
		<HistoryListItemContainer $isDone={isDone}>
			<div className="history-logo">
				<Coin />
			</div>
			<div className="history-info">
				<div>
					<span className="title">{history.qrCodeTitle}</span>
				</div>
				<div>
					송금처 <span>{history.memberName}</span>
				</div>
				<div>
					반환일시 <span className="return-datetime">{history.tradeDate}</span>
				</div>
			</div>
			<div className="history-money-info">
				<Right />
			</div>
		</HistoryListItemContainer>
	);
}

export default DepositHistoryItem;
