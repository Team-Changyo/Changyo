import React, { useEffect, useState } from 'react';
import { ISettlementGroup } from 'types/deposit';
import ListTotalText from 'components/atoms/common/ListTotalText';
import { findAllSettlementGroupApi } from 'utils/apis/deposit';
import { isAxiosError } from 'axios';
import toast from 'react-hot-toast';
import SettlementGroupListSkeleton from 'components/atoms/skeleton/SettlementGroupListSkeleton';
import { SettlementGroupListContainer } from './style';
import SettlementGroupListItem from '../SettlementGroupListItem';

function SettlementGroupList() {
	const [settlementGroups, setSettlementGroups] = useState<ISettlementGroup[]>([]);
	const [isLoading, setIsLoading] = useState(true);

	const fetchSettlementGroupsData = async () => {
		try {
			const response = await findAllSettlementGroupApi();
			console.log('그룹 데이터', response);

			if (response.status === 200) {
				setSettlementGroups(response.data.data.depositOverviews);

				setTimeout(() => {
					setIsLoading(false);
				}, 400);
			}
		} catch (error) {
			if (isAxiosError(error)) {
				console.error(error);
				toast.error(error.response?.data.message);
			}
		}
	};

	useEffect(() => {
		fetchSettlementGroupsData();
	}, []);

	return (
		<SettlementGroupListContainer>
			<ListTotalText text="관리 중" totalCnt={settlementGroups.length} />
			{isLoading ? (
				<SettlementGroupListSkeleton />
			) : settlementGroups.length ? (
				settlementGroups.map((el, idx) => <SettlementGroupListItem key={idx} settlementGroup={el} />)
			) : (
				<div>현재 관리중인 정산건이 없습니다.</div>
			)}
		</SettlementGroupListContainer>
	);
}

export default SettlementGroupList;
