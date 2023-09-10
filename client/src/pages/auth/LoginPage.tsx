import React from 'react';
import PageLayout from 'layouts/common/PageLayout';
import { ReactComponent as ColorChangyoLogo } from 'assets/imgs/ColorChangyoLogo.svg';
import { ReactComponent as ShinhanBankLogo } from 'assets/imgs/ShinhanBankLogo.svg';
import LoginPageLayout from 'layouts/page/auth/LoginPageLayout';
import LoginForm from 'components/organisms/auth/LoginForm';

function LoginPage() {
	return (
		<PageLayout>
			<LoginPageLayout
				ChangyoLogo={<ColorChangyoLogo />}
				LoginForm={<LoginForm />}
				ShinhanBankLogo={<ShinhanBankLogo />}
			/>
		</PageLayout>
	);
}

export default LoginPage;
