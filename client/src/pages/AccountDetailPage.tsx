import AccountSummary from 'components/organisms/account/AccountSummary';
import HistoryFilterList from 'components/organisms/account/HistoryFilterList';
import HistoryListStack from 'components/organisms/account/HistoryListStack';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import PageLayout from 'layouts/common/PageLayout';
import AccountDetailPageLayout from 'layouts/page/AccountDetailPageLayout';
import React, { useState } from 'react';

function AccountDetailPage() {
	const [selectedMenu, setSelcetedMenu] = useState('0');
	return (
		<PageLayout>
			<AccountDetailPageLayout
				Navbar={<SubTabNavbar text="계좌명" type="back" />}
				AccountSummary={<AccountSummary bankCode="088" accountNumber="123456789" totalMoney={20000} />}
				HistoryFilterList={<HistoryFilterList selectedMenu={selectedMenu} setSelcetedMenu={setSelcetedMenu} />}
				HistoryList={<HistoryListStack />}
			/>
		</PageLayout>
	);
}

export default AccountDetailPage;
