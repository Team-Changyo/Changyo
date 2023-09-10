import React, { useState } from 'react';
import PageLayout from 'layouts/common/PageLayout';
import AccountPageLayout from 'layouts/page/account/AccountPageLayout';
import AccountTotalSummary from 'components/organisms/account/AccountTotalSummary';
import AccountFilterList from 'components/organisms/account/AccountFilterList';
import AccountList from 'components/organisms/account/AccountList';
import MainTabNavbar from 'components/organisms/common/MainTabNavbar';
import { useNavigate } from 'react-router-dom';
import UnderlineButton from 'components/atoms/common/UnderlineButton';

function AccountPage() {
	const [selectedCode, setSelectedCode] = useState('000');
	const navigate = useNavigate();

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
				AccountSummary={<AccountTotalSummary accountCnt={2} totalMoney={200501} />}
				AccountFilterList={
					<AccountFilterList bankCodes={[]} selectedCode={selectedCode} setSelectedCode={setSelectedCode} />
				}
				AccountList={<AccountList accountList={[{ code: '000' }]} selectedCode={selectedCode} />}
			/>
		</PageLayout>
	);
}

export default AccountPage;
