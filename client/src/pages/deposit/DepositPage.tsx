import React, { useEffect, useState } from 'react';
import MainTabNavbar from 'components/organisms/common/MainTabNavbar';
import PageLayout from 'layouts/common/PageLayout';
import DepositPageLayout from 'layouts/page/deposit/DepositPageLayout';
import SelectTabTypeList from 'components/organisms/common/SelectTabTypeList';
import RemitHistoryListStack from 'components/organisms/deposit/DepositHistoryListStack';
import SettlementGroupList from 'components/organisms/deposit/SettlementGroupList';
import { useRecoilState } from 'recoil';
import { memberInfoState } from 'store/member';
import { findAllSettlementGroupApi } from 'utils/apis/deposit';
import { isAxiosError } from 'axios';
import { toast } from 'react-hot-toast';
import { ISettlementGroup } from 'types/deposit';
import { DUMMY_SETTLEMENT_GROUP_LIST } from 'utils/apis/dummy';

function DepositPage() {
	const [memberInfo] = useRecoilState(memberInfoState);
	const [tabType, setTabType] = useState(memberInfo?.role === 'MEMBER' ? 0 : 1);
	const [settlementGroups, setSettlementGroups] = useState<ISettlementGroup[]>([]);

	const tabTypes = [
		{ idx: 0, title: '송금 내역', handleClick: () => setTabType(0), selected: tabType },
		{ idx: 1, title: '정산 현황', handleClick: () => setTabType(1), selected: tabType },
	];

	const fetchSettlementGroupsData = async () => {
		try {
			const response = await findAllSettlementGroupApi();

			if (response.status === 200) {
				// setSettlementGroups(response.data.data.depositOverviews);
				setSettlementGroups(DUMMY_SETTLEMENT_GROUP_LIST);
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
		<PageLayout>
			<DepositPageLayout
				Navbar={<MainTabNavbar tabName="보증금 관리" />}
				SelectSubTab={<SelectTabTypeList tabTypes={tabTypes} />}
				SubTab={tabType ? <SettlementGroupList settlementGroups={settlementGroups} /> : <RemitHistoryListStack />}
			/>
		</PageLayout>
	);
}

export default DepositPage;
