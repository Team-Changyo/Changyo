import React from 'react';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import SettlementSubtab from 'components/organisms/deposit/SettlementSubtab';
import PageLayout from 'layouts/common/PageLayout';
import SettlementDetailLayout from 'layouts/page/deposit/SettlementDetailLayout';
import SettlementGroupInfo from 'components/organisms/deposit/SettlementGroupInfo';
import { useParams } from 'react-router-dom';

function SettlementDetail() {
	const location = useParams();

	console.log(location.sid);

	const settlementGroup = { key: 0, title: '럭셔리 글램핑 객실이용', moneyUnit: 20000, cntBeforeReturn: 2 };

	return (
		<PageLayout>
			<SettlementDetailLayout
				Navbar={<SubTabNavbar text="보증금 정산관리 상세" type="back" />}
				SettlementInfo={<SettlementGroupInfo settlementGroup={settlementGroup} />}
				SubTab={<SettlementSubtab settlementGroup={settlementGroup} />}
			/>
		</PageLayout>
	);
}

export default SettlementDetail;
