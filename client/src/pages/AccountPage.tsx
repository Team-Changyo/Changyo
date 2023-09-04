import React from 'react';
import PageLayout from 'layouts/common/PageLayout';
import AccountPageLayout from 'layouts/page/AccountPageLayout';
import AccountSummary from 'components/organisms/account/AccountSummary';

function AccountPage() {
	return (
		<PageLayout>
			<AccountPageLayout AccountSummary={<AccountSummary />} AccountInfo={<div>전체, 신한은행</div>} />
		</PageLayout>
	);
}

export default AccountPage;
