import React, { useEffect, useState } from 'react';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import SettlementSubtab from 'components/organisms/deposit/SettlementSubtab';
import PageLayout from 'layouts/common/PageLayout';
import SettlementDetailLayout from 'layouts/page/deposit/SettlementDetailLayout';
import SettlementGroupInfo from 'components/organisms/deposit/SettlementGroupInfo';
import { useParams } from 'react-router-dom';
import { isAxiosError } from 'axios';
import { toast } from 'react-hot-toast';
import { findAllSettlementApi } from 'utils/apis/deposit';
import { ISettlementGroup } from 'types/deposit';

function SettlementDetail() {
	const location = useParams();
	const [settlementGroup, setSettlementGroup] = useState<ISettlementGroup>({
		qrCodeId: 0,
		amount: 0,
		qrCodeTitle: '',
		remainCount: 0,
		remainTotal: 0,
	});

	const fetchSettlementGroup = async () => {
		try {
			if (location.sid) {
				const response = await findAllSettlementApi(location.sid);

				if (response.status === 200) {
					setSettlementGroup(response.data.data);
					console.log(response);
				}
			}
		} catch (error) {
			console.error(error);
			if (isAxiosError(error)) {
				toast.error(error.response?.data.message);
			}
		}
	};

	useEffect(() => {
		fetchSettlementGroup();
	}, []);

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
