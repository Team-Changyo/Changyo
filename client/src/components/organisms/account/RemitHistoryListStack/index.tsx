import React from 'react';
import { IDetailInfo } from 'types/account';
import { RemitHistoryListStackContianer } from './style';
import HistoryList from '../RemitHistoryList';

function RemitHistoryListStack({ detailInfo }: { detailInfo: IDetailInfo }) {
	console.log(detailInfo);
	const dates = Object.keys(detailInfo?.allTradeResponses);

	return (
		<RemitHistoryListStackContianer>
			{dates.map((el: string) => (
				<div key={el} className="history-list">
					<div className="date">{el}</div>
					<HistoryList histories={detailInfo.allTradeResponses[el]} />
				</div>
			))}
		</RemitHistoryListStackContianer>
	);
}

export default RemitHistoryListStack;
