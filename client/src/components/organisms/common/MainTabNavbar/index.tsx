import React, { ReactNode } from 'react';
import NavbarLayout from 'layouts/common/NavbarLayout';
import { MainTabNavbarContainer } from './style';

/**
 * 탭 타이틀만 적혀있는 상단 네비게이션바
 */
function MainTabNavbar({ tabName, navBtn }: { tabName: string; navBtn?: ReactNode }) {
	return (
		<NavbarLayout>
			<MainTabNavbarContainer>
				<h1>{tabName}</h1>
				<div className="nav-btn">{navBtn}</div>
			</MainTabNavbarContainer>
		</NavbarLayout>
	);
}

export default MainTabNavbar;
