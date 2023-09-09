import React, { useState } from 'react';
import PageLayout from 'layouts/common/PageLayout';
import AccountPageLayout from 'layouts/page/account/AccountPageLayout';
import AccountTotalSummary from 'components/organisms/account/AccountTotalSummary';
import AccountFilterList from 'components/organisms/account/AccountFilterList';
import AccountList from 'components/organisms/account/AccountList';
import MainTabNavbar from 'components/organisms/common/MainTabNavbar';

function AccountPage() {
	const [selectedCode, setSelectedCode] = useState('000');

	// TODO : api 연결 후 수정
	return (
		<PageLayout>
			<AccountPageLayout
				Navbar={<MainTabNavbar tabName="내 계좌" />}
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
