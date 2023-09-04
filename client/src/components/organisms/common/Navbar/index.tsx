import React from 'react';
import { NavbarContainer, NavbarWrapper } from './style';
import TabHeader from '../TabHeader';

function Navbar() {
	return (
		<NavbarWrapper>
			<NavbarContainer>
				<TabHeader tabName="내 계좌" />
			</NavbarContainer>
		</NavbarWrapper>
	);
}

export default Navbar;
