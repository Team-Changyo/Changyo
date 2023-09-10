import React, { ReactNode } from 'react';
import { QRPageLayoutContainer } from './style';

interface IQRPageLayoutProps {
	Navbar: ReactNode;
	CreateQRButton: ReactNode;
	QRList: ReactNode;
}

function QRPageLayout({ Navbar, CreateQRButton, QRList }: IQRPageLayoutProps) {
	return (
		<QRPageLayoutContainer>
			<div className="navbar">{Navbar}</div>
			<div className="create-qr-btn">{CreateQRButton}</div>
			<div className="qr-list">{QRList}</div>
		</QRPageLayoutContainer>
	);
}

export default QRPageLayout;
