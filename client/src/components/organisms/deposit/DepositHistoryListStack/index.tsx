import React from 'react';
import ListTotalText from 'components/atoms/common/ListTotalText';
import { DepositHistoryListStackContainer } from './style';
import DepositHistoryList from '../DepositHistoryList';

function DepositHistoryListStack() {
	const history = {
		wait: [
			{
				key: 1,
				title: '럭셔리 글램핑 객실 이용',
				remitDestination: '전인혁',
				moneyUnit: 20000,
				returnDateTime: '반환 전',
			},
			{
				key: 2,
				title: '럭셔리 글램핑 2호점 객실 이용',
				remitDestination: '최인혁',
				moneyUnit: 20000,
				returnDateTime: '반환 전',
			},
		],
		done: [
			{
				key: 3,
				title: '럭셔리 글램핑 2호점 객실 이용',
				remitDestination: '박인혁',
				moneyUnit: 20000,
				returnDateTime: '2023-03-08 14:33',
			},
			{
				key: 4,
				title: '럭셔리 글램핑 객실 이용',
				remitDestination: '김인혁',
				moneyUnit: 20000,
				returnDateTime: '2022-02-04 16:33',
			},
			{
				key: 5,
				title: '럭셔리 글램핑 2호점 객실 이용',
				remitDestination: '박인혁',
				moneyUnit: 20000,
				returnDateTime: '2023-03-08 14:33',
			},
			{
				key: 6,
				title: '럭셔리 글램핑 객실 이용',
				remitDestination: '김인혁',
				moneyUnit: 20000,
				returnDateTime: '2022-02-04 16:33',
			},
		],
	};

	// TODO API
	return (
		<DepositHistoryListStackContainer>
			<div className="return-wait">
				<ListTotalText text="반환대기" totalCnt={2} />
				<DepositHistoryList histories={history.wait} isDone={false} />
			</div>
			<div className="return-done">
				<ListTotalText text="반환완료" totalCnt={4} />
				<DepositHistoryList histories={history.done} isDone />
			</div>
		</DepositHistoryListStackContainer>
	);
}

export default DepositHistoryListStack;
