import React, { ReactNode } from 'react';
import { LoadingPageLayoutContainer } from './style';

interface ILoadingPageLayoutProps {
	ChangyoLogo: ReactNode;
	ShinhanBankLogo: ReactNode;
}
function LoadingPageLayout({ ChangyoLogo, ShinhanBankLogo }: ILoadingPageLayoutProps) {
	return (
		<LoadingPageLayoutContainer>
			<div className="changyo-logo">{ChangyoLogo}</div>
			<div className="shinhan-bank-logo">{ShinhanBankLogo}</div>
		</LoadingPageLayoutContainer>
	);
}

export default LoadingPageLayout;
