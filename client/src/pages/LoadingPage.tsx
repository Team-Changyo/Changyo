import React from 'react';
import { ReactComponent as WhiteChangyoLogo } from 'assets/imgs/WhiteChangyoLogo.svg';
import { ReactComponent as ShinhanBankLogo } from 'assets/imgs/ShinhanBankLogo.svg';
import LoadingPageLayout from 'layouts/page/LoadingPageLayout';

function LoadingPage() {
	return <LoadingPageLayout ChangyoLogo={<WhiteChangyoLogo />} ShinhanBankLogo={<ShinhanBankLogo />} />;
}

export default LoadingPage;
