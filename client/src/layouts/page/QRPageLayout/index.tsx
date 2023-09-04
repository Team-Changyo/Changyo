import React, { ReactNode } from 'react';
import Button from 'components/organisms/common/Button';
import { QRPageLayoutContainer } from './style';

interface IQRPageLayoutProps {
	Navbar: ReactNode;
}
function QRPageLayout({ Navbar }: IQRPageLayoutProps) {
	return (
		<QRPageLayoutContainer>
			<div className="navbar">{Navbar}</div>
			<Button handleClick={() => {}} text="한건씩 반환" type="Danger" />
		</QRPageLayoutContainer>
	);
}

export default QRPageLayout;
