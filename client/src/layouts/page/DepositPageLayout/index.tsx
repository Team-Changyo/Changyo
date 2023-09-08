import React, { ReactNode } from 'react';
import { DepositPageLayoutContainer } from './style';

interface IDepositPageLayoutProps {
	Navbar: ReactNode;
	SelectSubTab: ReactNode;
	SubTab: ReactNode;
}
function DepositPageLayout({ Navbar, SelectSubTab, SubTab }: IDepositPageLayoutProps) {
	return (
		<DepositPageLayoutContainer>
			<div className="navbar">{Navbar}</div>
			<div className="select-sub-tab">{SelectSubTab}</div>
			<div className="subtab">{SubTab}</div>
		</DepositPageLayoutContainer>
	);
}

export default DepositPageLayout;
