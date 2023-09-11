import React, { ReactNode } from 'react';
import { AccountRegisterPageLayoutContainer } from './style';

interface IAccountRegisterPageLayoutProps {
	Navbar: ReactNode;
	SelectBankTitle: ReactNode;
	SelectBank: ReactNode;
	InputAccountNumberTitle: ReactNode;
	InputAccountNumber: ReactNode;
	InputAccountAliasTitle: ReactNode;
	InputAccountAlias: ReactNode;
	CertAccountBtn: ReactNode;
	CheckIsMainAccount: ReactNode;
	AccountRegisterBtn: ReactNode;
	CertModal: ReactNode;
}
function AccountRegisterPageLayout({
	Navbar,
	SelectBankTitle,
	SelectBank,
	InputAccountNumberTitle,
	InputAccountNumber,
	InputAccountAliasTitle,
	InputAccountAlias,
	CertAccountBtn,
	CheckIsMainAccount,
	AccountRegisterBtn,
	CertModal,
}: IAccountRegisterPageLayoutProps) {
	return (
		<AccountRegisterPageLayoutContainer>
			<div className="navbar">{Navbar}</div>
			<div className="select-bank-title title-mb">{SelectBankTitle}</div>
			<div className="select-bank group-mb">{SelectBank}</div>
			<div className="input-account-number-title title-mb">{InputAccountNumberTitle}</div>
			<div className="input-account-number group-mb">{InputAccountNumber}</div>
			<div className="input-account-alias-title title-mb">{InputAccountAliasTitle}</div>
			<div className="input-account-alias group-mb">{InputAccountAlias}</div>
			<div className="cert-account-btn">{CertAccountBtn}</div>
			<div className="bottom">
				<div className="check-is-main-account">{CheckIsMainAccount}</div>
				<div className="account-register-btn">{AccountRegisterBtn}</div>
			</div>
			<div className="cert-modal">{CertModal}</div>
		</AccountRegisterPageLayoutContainer>
	);
}

export default AccountRegisterPageLayout;
