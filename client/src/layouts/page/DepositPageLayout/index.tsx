import React, { ReactNode } from 'react';
import { DepositPageLayoutContainer } from './style';

interface IDepositPageLayoutProps {
	Navbar: ReactNode;
}
function DepositPageLayout({ Navbar }: IDepositPageLayoutProps) {
	return (
		<DepositPageLayoutContainer>
			<div className="navbar">{Navbar}</div>
		</DepositPageLayoutContainer>
	);
}

export default DepositPageLayout;
