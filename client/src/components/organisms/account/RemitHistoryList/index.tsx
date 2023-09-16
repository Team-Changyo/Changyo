import React from 'react';
import RemitHistoryListItem from 'components/organisms/account/RemitHistoryListItem';
import { ITradeHistory } from 'types/account';
import { RemitHistoryListWrapper } from './style';

function RemitHistoryList({ histories }: { histories: ITradeHistory[] }) {
	return (
		<RemitHistoryListWrapper>
			{histories.map((el, idx) => {
				return <RemitHistoryListItem key={idx} history={el} />;
			})}
		</RemitHistoryListWrapper>
	);
}

export default RemitHistoryList;
