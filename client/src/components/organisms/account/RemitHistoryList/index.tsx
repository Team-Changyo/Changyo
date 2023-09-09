import React from 'react';
import { IHistory } from 'types/account';
import RemitHistoryListItem from 'components/organisms/account/RemitHistoryListItem';
import { RemitHistoryListWrapper } from './style';

function RemitHistoryList({ histories }: { histories: IHistory[] }) {
	return (
		<RemitHistoryListWrapper>
			{histories.map((el) => {
				return <RemitHistoryListItem key={el.key} history={el} />;
			})}
		</RemitHistoryListWrapper>
	);
}

export default RemitHistoryList;
