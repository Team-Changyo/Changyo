import React, { ReactNode } from 'react';
import { AccountDetailPageLayoutContainer } from './style';

interface IAccountDetailPageLayoutProps {
	Navbar: ReactNode;
	AccountSummary: ReactNode;
	HistoryFilterList: ReactNode;
	HistoryList: ReactNode;
}

function AccountDetailPageLayout({
	Navbar,
	AccountSummary,
	HistoryFilterList,
	HistoryList,
}: IAccountDetailPageLayoutProps) {
	return (
		<AccountDetailPageLayoutContainer>
			<div className="navbar">{Navbar}</div>
			<div className="account-summary">{AccountSummary}</div>
			<div className="history-filter-list">{HistoryFilterList}</div>
			<div className="history-list">{HistoryList}</div>
		</AccountDetailPageLayoutContainer>
	);
}

export default AccountDetailPageLayout;
