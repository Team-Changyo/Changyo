import React, { ReactNode } from 'react';
import { LoginPageLayoutContainer } from './style';

interface ILoginPageLayoutProps {
	ChangyoLogo: ReactNode;
	LoginForm: ReactNode;
	ShinhanBankLogo: ReactNode;
}
function LoginPageLayout({ ChangyoLogo, LoginForm, ShinhanBankLogo }: ILoginPageLayoutProps) {
	return (
		<LoginPageLayoutContainer>
			<div className="changyo-logo">{ChangyoLogo}</div>
			<div className="login-form">{LoginForm}</div>
			<div className="shinhan-bank-logo">{ShinhanBankLogo}</div>
		</LoginPageLayoutContainer>
	);
}

export default LoginPageLayout;
