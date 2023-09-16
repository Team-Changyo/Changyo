import React, { ReactNode } from 'react';
import NavbarLayout from 'layouts/common/NavbarLayout';
import { useRecoilState } from 'recoil';
import { memberInfoState } from 'store/member';
import { MainTabNavbarContainer } from './style';

/**
 * 탭 타이틀만 적혀있는 상단 네비게이션바
 */
function MainTabNavbar({ tabName, navBtn }: { tabName: string; navBtn?: ReactNode }) {
	const [memberState, setMemberState] = useRecoilState(memberInfoState);

	const clearStorage = () => {
		localStorage.removeItem('accessToken');
		localStorage.removeItem('refreshToken');
		setMemberState(null);
		console.log(localStorage);
		console.log(memberState);
	};

	return (
		<NavbarLayout>
			<MainTabNavbarContainer>
				<h1>{tabName}</h1>
				<button type="button" onClick={clearStorage}>
					로그아웃
				</button>
				<div className="nav-btn">{navBtn}</div>
			</MainTabNavbarContainer>
		</NavbarLayout>
	);
}

export default MainTabNavbar;
