import React, { useEffect, useState } from 'react';
import ListTotalText from 'components/atoms/common/ListTotalText';
import { findAllDoneRemitHistoryApi, findAllWaitRemitHistoryApi } from 'utils/apis/deposit';
import { IDepositHistory } from 'types/deposit';
import DepositHistoryListSkeleton from 'components/atoms/skeleton/DepositHistoryListSkeleton';
import { DepositHistoryListStackContainer } from './style';
import DepositHistoryList from '../DepositHistoryList';

function DepositHistoryListStack() {
	const [waitHistories, setWaitHistories] = useState<IDepositHistory[]>([]);
	const [doneHistories, setDoneHistories] = useState<IDepositHistory[]>([]);
	const [waitIsLoading, setWaitIsLoading] = useState(true);
	const [doneIsLoading, setDoneIsLoading] = useState(true);

	const fetchWaitData = async () => {
		try {
			const response = await findAllWaitRemitHistoryApi();

			if (response.status === 200) {
				setWaitHistories(response.data.data.waitWithdrawals);
				setTimeout(() => {
					setWaitIsLoading(false);
				}, 300);
			}
		} catch (error) {
			console.error(error);
		}
	};

	const fetchDoneData = async () => {
		try {
			const response = await findAllDoneRemitHistoryApi();

			if (response.status === 200) {
				setDoneHistories(response.data.data.doneWithdrawals);
				setTimeout(() => {
					setDoneIsLoading(false);
				}, 300);
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
				{waitIsLoading ? (
					<DepositHistoryListSkeleton />
				) : waitHistories.length ? (
					<DepositHistoryList histories={waitHistories} isDone={false} />
				) : (
					<div>반환 대기중인 송금건이 없습니다.</div>
				)}
			</div>
			<div className="return-done">
				<ListTotalText text="반환완료" totalCnt={doneHistories.length} />
				{doneIsLoading ? (
					<DepositHistoryListSkeleton />
				) : doneHistories.length ? (
					<DepositHistoryList histories={doneHistories} isDone />
				) : (
					<div>반환 완료된 송금건이 없습니다.</div>
				)}
			</div>
		</DepositHistoryListStackContainer>
	);
}

export default DepositHistoryListStack;
