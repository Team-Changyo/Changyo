import React from 'react';
import { HistoryListStackContianer } from './style';
import HistoryList from '../HistoryList';

function HistoryListStack() {
	const history = [
		{
			date: '2023-09-04',
			history: [
				{ key: 0, title: '아이센스PC방', time: '23:23', type: 'deposit', price: -11000, balance: 200501 },
				{ key: 1, title: '아이센스PC방', time: '23:23', type: 'deposit', price: -11000, balance: 200501 },
			],
		},
		{
			date: '2023-09-05',
			history: [
				{ key: 2, title: '아이센스PC방', time: '23:23', type: 'deposit', price: -11000, balance: 200501 },
				{ key: 3, title: '아이센스PC방', time: '23:23', type: 'deposit', price: -11000, balance: 200501 },
			],
		},
	];

	return (
		<HistoryListStackContianer>
			{history.map((el) => (
				<div key={el.date} className="history-list">
					<div className="date">{el.date}</div>
					<HistoryList histories={el.history} />
				</div>
			))}
		</HistoryListStackContianer>
	);
}

export default HistoryListStack;
