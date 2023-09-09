import React, { useState } from 'react';
import MainTabNavbar from 'components/organisms/common/MainTabNavbar';
import PageLayout from 'layouts/common/PageLayout';
import DepositPageLayout from 'layouts/page/DepositPageLayout';
import SelectTabTypeList from 'components/organisms/common/SelectTabTypeList';
import RemitHistoryListStack from 'components/organisms/deposit/DepositHistoryListStack';
import SettlementList from 'components/organisms/deposit/SettlementList';

function DepositPage() {
	const [tabType, setTabType] = useState(0);

	const tabTypes = [
		{ idx: 0, title: '송금 내역', handleClick: () => setTabType(0), selected: tabType },
		{ idx: 1, title: '정산 현황', handleClick: () => setTabType(1), selected: tabType },
	];

	const settlements = [
		{
			key: 1,
			title: '럭셔리 글램핑 객실이용',
			moneyUnit: 20000,
			cntBeforeReturn: 3,
		},
		{
			key: 2,
			title: '럭셔리 2호점 글램핑 객실이용',
			moneyUnit: 20000,
			cntBeforeReturn: 0,
		},
	];
	return (
		<PageLayout>
			<DepositPageLayout
				Navbar={<MainTabNavbar tabName="보증금 관리" />}
				SelectSubTab={<SelectTabTypeList tabTypes={tabTypes} />}
				SubTab={tabType ? <RemitHistoryListStack /> : <SettlementList settlements={settlements} />}
			/>
		</PageLayout>
	);
}

export default DepositPage;
