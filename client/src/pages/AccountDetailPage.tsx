import React, { useState } from 'react';
import AccountSummary from 'components/organisms/account/AccountSummary';
import RemitHistoryFilterList from 'components/organisms/account/RemitHistoryFilterList';
import RemitHistoryListStack from 'components/organisms/account/RemitHistoryListStack';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import PageLayout from 'layouts/common/PageLayout';
import AccountDetailPageLayout from 'layouts/page/AccountDetailPageLayout';

function AccountDetailPage() {
	const [selectedMenu, setSelcetedMenu] = useState('0');
	return (
		<PageLayout>
			<AccountDetailPageLayout
				Navbar={<SubTabNavbar text="계좌명" type="back" />}
				AccountSummary={<AccountSummary bankCode="088" accountNumber="123456789" totalMoney={20000} />}
				RemitHistoryFilterList={
					<RemitHistoryFilterList selectedMenu={selectedMenu} setSelcetedMenu={setSelcetedMenu} />
				}
				RemitHistoryList={<RemitHistoryListStack />}
			/>
		</PageLayout>
	);
}

export default AccountDetailPage;
