import React, { ReactNode } from 'react';
import { AccountPageLayoutContainer } from './style';

interface AccountPageLayoutProps {
	AccountSummary: ReactNode;
	AccountInfo: ReactNode;
}

function AccountPageLayout({ AccountSummary, AccountInfo }: AccountPageLayoutProps) {
	return (
		<AccountPageLayoutContainer>
			<div className="account-summary">{AccountSummary}</div>
			<div className="account-info">{AccountInfo}</div>
		</AccountPageLayoutContainer>
	);
}

export default AccountPageLayout;
