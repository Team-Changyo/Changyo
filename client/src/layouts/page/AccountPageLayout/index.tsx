import React, { ReactNode } from 'react';
import { AccountPageLayoutContainer } from './style';

interface AccountPageLayoutProps {
	AccountSummary: ReactNode;
	AccountFilterList: ReactNode;
	AccountList: ReactNode;
}

function AccountPageLayout({ AccountSummary, AccountFilterList, AccountList }: AccountPageLayoutProps) {
	return (
		<AccountPageLayoutContainer>
			<div className="account-summary">{AccountSummary}</div>
			<div className="account-filter">{AccountFilterList}</div>
			<div className="account-list">{AccountList}</div>
		</AccountPageLayoutContainer>
	);
}

export default AccountPageLayout;
