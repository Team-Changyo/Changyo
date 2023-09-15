import React, { useEffect, useState } from 'react';
import ListTotalText from 'components/atoms/common/ListTotalText';
import { findAllWaitRemitHistoryApi } from 'utils/apis/deposit';
import { IDepositHistory } from 'types/deposit';
import { DepositHistoryListStackContainer } from './style';
import DepositHistoryList from '../DepositHistoryList';

function DepositHistoryListStack() {
	const [waitHistories, setWaitHistories] = useState<IDepositHistory[]>([]);
	const [doneHistories, setDoneHistories] = useState<IDepositHistory[]>([]);

	const fetchWaitData = async () => {
		try {
			const response = await findAllWaitRemitHistoryApi();

			console.log('wait', response);
			if (response.status === 200) {
				setWaitHistories(response.data.data.waitWithdrawals);
			}
		} catch (error) {
			console.error(error);
		}
	};

	const fetchDoneData = async () => {
		try {
			const response = await findAllWaitRemitHistoryApi();

			console.log('done', response);
			if (response.status === 200) {
				setDoneHistories(response.data.data.waitWithdrawals);
			}
		} catch (error) {
			console.error(error);
		}
	};

	useEffect(() => {
		fetchWaitData();
		fetchDoneData();
	}, []);

	return (
		<DepositHistoryListStackContainer>
			<div className="return-wait">
				<ListTotalText text="반환대기" totalCnt={waitHistories.length} />
				{waitHistories.length ? (
					<DepositHistoryList histories={waitHistories} isDone={false} />
				) : (
					<div>반환 대기중인 정산건이 없습니다.</div>
				)}
			</div>
			<div className="return-done">
				<ListTotalText text="반환완료" totalCnt={doneHistories.length} />
				{doneHistories.length ? (
					<DepositHistoryList histories={doneHistories} isDone />
				) : (
					<div>반환 대기중인 정산건이 없습니다.</div>
				)}
			</div>
		</DepositHistoryListStackContainer>
	);
}

export default DepositHistoryListStack;
