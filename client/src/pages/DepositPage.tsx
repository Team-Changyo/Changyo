import MainTabNavbar from 'components/organisms/common/MainTabNavbar';
import DepositPageLayout from 'layouts/page/DepositPageLayout';
import React from 'react';

function DepositPage() {
	return <DepositPageLayout Navbar={<MainTabNavbar tabName="보증금 관리" />} />;
}

export default DepositPage;
