import React from 'react';
import RemitHistoryListItem from 'components/organisms/account/RemitHistoryListItem';
import { ITradeHistory } from 'types/account';
import { RemitHistoryListWrapper } from './style';

function RemitHistoryList({ histories }: { histories: ITradeHistory[] }) {
	return (
		<RemitHistoryListWrapper>
			{histories.map((el) => {
				return <RemitHistoryListItem key={el.tradeTime} history={el} />;
			})}
		</RemitHistoryListWrapper>
	);
}

export default RemitHistoryList;
