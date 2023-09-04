import React, { ReactNode } from 'react';
import { NavbarLayoutContainer, NavbarLayoutWrapper } from './style';

function NavbarLayout({ children }: { children: ReactNode }) {
	return (
		<NavbarLayoutWrapper>
			<NavbarLayoutContainer>{children}</NavbarLayoutContainer>
		</NavbarLayoutWrapper>
	);
}

export default NavbarLayout;
