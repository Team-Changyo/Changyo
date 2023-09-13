import React, { useEffect, useState } from 'react';
import PageLayout from 'layouts/common/PageLayout';
import AccountPageLayout from 'layouts/page/account/AccountPageLayout';
import AccountTotalSummary from 'components/organisms/account/AccountTotalSummary';
import AccountFilterList from 'components/organisms/account/AccountFilterList';
import AccountList from 'components/organisms/account/AccountList';
import MainTabNavbar from 'components/organisms/common/MainTabNavbar';
import { useNavigate } from 'react-router-dom';
import UnderlineButton from 'components/atoms/common/UnderlineButton';
import { findAllAccountApi } from 'utils/apis/account';
import { isAxiosError } from 'axios';
import { toast } from 'react-hot-toast';
import { IAccount } from 'types/account';

function AccountPage() {
	const [selectedCode, setSelectedCode] = useState('000'); // 은행구분
	const [accountList, setAccountList] = useState<IAccount[]>([]);
	const [totalMoney, setTotalMoney] = useState(0);
	const navigate = useNavigate();

	const fetchAccountData = async () => {
		try {
			const response = await findAllAccountApi();
			if (response.status === 200) {
				setAccountList(response.data.data.accountDetailResponses);
				setTotalMoney(response.data.data.accountSize);
			}
		} catch (error) {
			if (isAxiosError(error)) {
				toast.error(error.response?.data.message);
			}
		}
	};

	useEffect(() => {
		fetchAccountData();
	}, []);
	// TODO : api 연결 후 수정
	return (
		<PageLayout>
			<AccountPageLayout
				Navbar={
					<MainTabNavbar
						tabName="내 계좌"
						navBtn={<UnderlineButton text="계좌 등록하기" handleClick={() => navigate('register')} type="Primary" />}
					/>
				}
				AccountSummary={<AccountTotalSummary accountCnt={accountList.length} totalMoney={totalMoney} />}
				AccountFilterList={
					<AccountFilterList bankCodes={['088']} selectedCode={selectedCode} setSelectedCode={setSelectedCode} />
				}
				AccountList={<AccountList accountList={accountList} selectedCode={selectedCode} />}
			/>
		</PageLayout>
	);
}

export default AccountPage;
