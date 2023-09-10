import React, { ReactNode } from 'react';
import { SettlementDetailLayoutContainer } from './style';

interface ISettlementDetailLayoutProps {
	Navbar: ReactNode;
	SettlementInfo: ReactNode;
	SubTab: ReactNode;
}
function SettlementDetailLayout({ Navbar, SettlementInfo, SubTab }: ISettlementDetailLayoutProps) {
	return (
		<SettlementDetailLayoutContainer>
			<div className="navbar">{Navbar}</div>
			<div className="select-sub-tab">{SettlementInfo}</div>
			<div className="subtab">{SubTab}</div>
		</SettlementDetailLayoutContainer>
	);
}

export default SettlementDetailLayout;
