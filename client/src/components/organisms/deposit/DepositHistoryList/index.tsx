import React from 'react';
import { IDepositHistory } from 'types/deposit';
import { DepositHistoryListWrapper } from './style';
import DepositHistoryItem from '../DepositHistoryListItem';

function DepositHistoryList({ histories, isDone }: { histories: IDepositHistory[]; isDone: boolean }) {
	return (
		<DepositHistoryListWrapper>
			{histories.map((el) => {
				return <DepositHistoryItem key={el.tradeId} history={el} isDone={isDone} />;
			})}
		</DepositHistoryListWrapper>
	);
}

export default DepositHistoryList;
