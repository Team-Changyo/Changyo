import React from 'react';
import MainTabNavbar from 'components/organisms/common/MainTabNavbar';
import QRPageLayout from 'layouts/page/QRPageLayout';
import PageLayout from 'layouts/common/PageLayout';

function QRPage() {
	return (
		<PageLayout>
			<QRPageLayout Navbar={<MainTabNavbar tabName="내 송금 QR" />} />
		</PageLayout>
	);
}

export default QRPage;
