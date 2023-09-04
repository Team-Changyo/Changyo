import React, { useState } from 'react';
import PageLayout from 'layouts/common/PageLayout';
import AccountPageLayout from 'layouts/page/AccountPageLayout';
import AccountSummary from 'components/organisms/account/AccountSummary';
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
				AccountSummary={<AccountSummary accountCnt={2} totalMoney={200501} />}
				AccountFilterList={
					<AccountFilterList bankCodes={[]} selectedCode={selectedCode} setSelectedCode={setSelectedCode} />
				}
				AccountList={<AccountList accountList={[{ code: '000' }]} selectedCode={selectedCode} />}
			/>
		</PageLayout>
	);
}

export default AccountPage;
