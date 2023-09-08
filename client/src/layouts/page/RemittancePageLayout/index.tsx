import React, { ReactNode } from 'react';
import { RemittancePageLayoutContainer } from './style';

interface IRemittancePageLayoutProps {
	Navbar: ReactNode;
	RemittanceInfo: ReactNode;
	ToAccountInfoTitle: ReactNode;
	ToAccountInfo: ReactNode;
	FromAccountInfoTitle: ReactNode;
	FromAccountInfo: ReactNode;
	MoneyUnitTitle: ReactNode;
	MoneyUnit: ReactNode;
	RemittanceGuideText: ReactNode;
	RemitButton: ReactNode;
}

function RemittancePageLayout({
	Navbar,
	RemittanceInfo,
	ToAccountInfoTitle,
	ToAccountInfo,
	FromAccountInfoTitle,
	FromAccountInfo,
	MoneyUnitTitle,
	MoneyUnit,
	RemittanceGuideText,
	RemitButton,
}: IRemittancePageLayoutProps) {
	return (
		<RemittancePageLayoutContainer>
			<div className="navbar">{Navbar}</div>
			<div className="remittance-info group-mb">{RemittanceInfo}</div>
			<div className="to-account-title title-mb">{ToAccountInfoTitle}</div>
			<div className="to-account-info  group-mb">{ToAccountInfo}</div>
			<div className="from-account-title title-mb">{FromAccountInfoTitle}</div>
			<div className="from-account-info  group-mb">{FromAccountInfo}</div>
			<div className="money-unit-title title-mb">{MoneyUnitTitle}</div>
			<div className="money-unit">{MoneyUnit}</div>
			<div className="remit">
				<div className="guide-text">{RemittanceGuideText}</div>
				<div className="remit-btn">{RemitButton}</div>
			</div>
		</RemittancePageLayoutContainer>
	);
}

export default RemittancePageLayout;
