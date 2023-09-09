import React, { ReactNode } from 'react';
import { CreateQRPageLayoutContainer } from './style';

interface ICreateQRPageLayoutProps {
	Navbar: ReactNode;
	RemittanceTypeTitle: ReactNode;
	SelectRemittanceType: ReactNode;
	AccountInfoTitle: ReactNode;
	SelectAccount: ReactNode;
	MoneyUnitTitle: ReactNode;
	InputMoneyUnit: ReactNode;
	CreateQRBtn: ReactNode;
	InputDisplayName?: ReactNode;
	DisplayNameTitle?: ReactNode;
}
function CreateQRPageLayout({
	Navbar,
	RemittanceTypeTitle,
	SelectRemittanceType,
	AccountInfoTitle,
	SelectAccount,
	MoneyUnitTitle,
	InputMoneyUnit,
	CreateQRBtn,
	InputDisplayName,
	DisplayNameTitle,
}: ICreateQRPageLayoutProps) {
	return (
		<CreateQRPageLayoutContainer>
			<div className="navbar">{Navbar}</div>
			<div className="select-remittance-type-title title-mb">{RemittanceTypeTitle}</div>
			<div className="select-tab-menu group-mb">{SelectRemittanceType}</div>
			<div className="select-account-info-title title-mb">{AccountInfoTitle}</div>
			<div className="select-account group-mb">{SelectAccount}</div>
			<div className="money-unit-title title-mb">{MoneyUnitTitle}</div>
			<div className="input-money-unit group-mb">{InputMoneyUnit}</div>
			<div className="display-name-title title-mb">{DisplayNameTitle}</div>
			<div className="input-display-name group-mb">{InputDisplayName}</div>
			<div className="create-qr-btn">{CreateQRBtn}</div>
		</CreateQRPageLayoutContainer>
	);
}

export default CreateQRPageLayout;
