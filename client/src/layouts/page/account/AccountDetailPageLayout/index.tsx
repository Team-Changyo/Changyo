import React, { ReactNode } from 'react';
import { AccountDetailPageLayoutContainer } from './style';

interface IAccountDetailPageLayoutProps {
	Navbar: ReactNode;
	AccountSummary: ReactNode;
	RemitHistoryFilterList: ReactNode;
	RemitHistoryList: ReactNode;
}

function AccountDetailPageLayout({
	Navbar,
	AccountSummary,
	RemitHistoryFilterList,
	RemitHistoryList,
}: IAccountDetailPageLayoutProps) {
	return (
		<AccountDetailPageLayoutContainer>
			<div className="navbar">{Navbar}</div>
			<div className="account-summary">{AccountSummary}</div>
			<div className="remit-history-filter-list">{RemitHistoryFilterList}</div>
			<div className="remit-history-list">{RemitHistoryList}</div>
		</AccountDetailPageLayoutContainer>
	);
}

export default AccountDetailPageLayout;
