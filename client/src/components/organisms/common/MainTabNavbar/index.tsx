import React from 'react';
import NavbarLayout from 'layouts/common/NavbarLayout';
import { MainTabNavbarContainer } from './style';

/**
 * 탭 타이틀만 적혀있는 상단 네비게이션바
 */
function MainTabNavbar({ tabName }: { tabName: string }) {
	return (
		<NavbarLayout>
			<MainTabNavbarContainer>
				<h1>{tabName}</h1>
			</MainTabNavbarContainer>
		</NavbarLayout>
	);
}

export default MainTabNavbar;
