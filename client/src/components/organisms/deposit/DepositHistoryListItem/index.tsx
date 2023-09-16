import React from 'react';
import { ReactComponent as Coin } from 'assets/icons/account/coin.svg';
// import { ReactComponent as Right } from 'assets/icons/navigation/right.svg';
import { IDepositHistory } from 'types/deposit';
import { formatMoney } from 'utils/common/formatMoney';
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
					<span className="title">{history.qrCodeTitle}</span> (
					<span className="member-name">{history.memberName}</span>)
				</div>
				<div>
					<span className="money">{formatMoney(history.amount)}원</span>
				</div>
				<div>
					<span className="return-datetime">{history.tradeDate}</span>
				</div>
			</div>

			{/* TODO : 보증금 관리 상세페이지 연결 */}
			<div className="history-money-info">{/* <Right /> */}</div>
		</HistoryListItemContainer>
	);
}

export default DepositHistoryItem;
