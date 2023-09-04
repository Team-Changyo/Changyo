import NavbarLayout from 'layouts/common/NavbarLayout';
import React from 'react';
import { SubTabNavbarContainer } from './style';

/**
 * 뒤로가기 버튼 + 중앙타이틀이 적혀있는 상단 네비게이션바
 */
function SubTabNavbar() {
	return (
		<NavbarLayout>
			<SubTabNavbarContainer>sub</SubTabNavbarContainer>
		</NavbarLayout>
	);
}

export default SubTabNavbar;
