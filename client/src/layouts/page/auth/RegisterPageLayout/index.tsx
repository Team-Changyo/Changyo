import React, { ReactNode } from 'react';
import PageLayout from 'layouts/common/PageLayout';
import { RegisterPageLayoutContainer } from './style';

interface IRegisterPageLayoutProps {
	Navbar: ReactNode;
	StepView: ReactNode;
}

function RegisterPageLayout({ Navbar, StepView }: IRegisterPageLayoutProps) {
	return (
		<PageLayout>
			<RegisterPageLayoutContainer>
				<div className="navbar">{Navbar}</div>
				<div className="step-view">{StepView}</div>
			</RegisterPageLayoutContainer>
		</PageLayout>
	);
}

export default RegisterPageLayout;
