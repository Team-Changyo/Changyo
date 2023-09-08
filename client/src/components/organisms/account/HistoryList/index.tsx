import React from 'react';
import { IHistory } from 'types/account';
import HistoryListItem from 'components/organisms/account/HistoryListItem';
import { HistoryListWrapper } from './style';

function HistoryList({ histories }: { histories: IHistory[] }) {
	return (
		<HistoryListWrapper>
			{histories.map((el) => {
				return <HistoryListItem key={el.key} history={el} />;
			})}
		</HistoryListWrapper>
	);
}

export default HistoryList;
