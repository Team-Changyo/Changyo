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
import { ISettlement } from 'types/deposit';

function SettlementDetail() {
	const location = useParams();
	const [title, setTitle] = useState('');
	const [moneyUnit, setMoneyUnit] = useState(0);
	const [totalMoney, setTotalMoney] = useState(0);
	const [waitSettlement, setWaitSettlement] = useState<ISettlement[]>([]);
	const [doneSettlement, setDoneSettlement] = useState<ISettlement[]>([]);

	const fetchSettlementGroup = async () => {
		try {
			if (location.sid) {
				const response = await findAllSettlementApi(location.sid);

				console.log(response);
				if (response.status === 200) {
					setTitle(response.data.data.qrCodeTitle);
					setMoneyUnit(response.data.data.amount);
					setTotalMoney(response.data.data.totalAmount);
					setWaitSettlement(response.data.data.waitDetails);
					setDoneSettlement(response.data.data.doneDetails);
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
				SettlementInfo={<SettlementGroupInfo title={title} moneyUnit={moneyUnit} totalMoney={totalMoney} />}
				SubTab={
					<SettlementSubtab
						waitSettlement={waitSettlement}
						doneSettlement={doneSettlement}
						title={title}
						moneyUnit={moneyUnit}
						fetchSettlementGroup={fetchSettlementGroup}
					/>
				}
			/>
		</PageLayout>
	);
}

export default SettlementDetail;
