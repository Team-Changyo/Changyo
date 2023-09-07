import React, { ReactNode } from 'react';
import { ViewQRPageLayoutContainer } from './style';

interface IViewQRPageLayoutProps {
	Navbar: ReactNode;
	RemittanceRequestInfo: ReactNode;
	MoneyUnit: ReactNode;
	QRImage: ReactNode;
	GuideText: ReactNode;
	QRShareBtn: ReactNode;
	LinkShareBtn: ReactNode;
}

function ViewQRPageLayout({
	Navbar,
	RemittanceRequestInfo,
	MoneyUnit,
	QRImage,
	GuideText,
	QRShareBtn,
	LinkShareBtn,
}: IViewQRPageLayoutProps) {
	return (
		<ViewQRPageLayoutContainer>
			<div className="navbar">{Navbar}</div>
			<div className="remittance-request-info">{RemittanceRequestInfo}</div>
			<div className="money-unit">{MoneyUnit}</div>
			<div className="qr-image">{QRImage}</div>
			<div className="guide-text">{GuideText}</div>
			<div className="share-btns">
				<div className="qr-share-btn">{QRShareBtn}</div>
				<div className="link-share-btn">{LinkShareBtn}</div>
			</div>
		</ViewQRPageLayoutContainer>
	);
}

export default ViewQRPageLayout;
