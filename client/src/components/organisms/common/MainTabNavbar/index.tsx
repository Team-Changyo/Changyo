import React, { ReactNode } from 'react';
import NavbarLayout from 'layouts/common/NavbarLayout';
import { useRecoilState } from 'recoil';
import { memberInfoState } from 'store/member';
import { Menu, MenuItem } from '@mui/material';
import { MainTabNavbarContainer } from './style';

/**
 * 탭 타이틀만 적혀있는 상단 네비게이션바
 */
function MainTabNavbar({ tabName, navBtn }: { tabName: string; navBtn?: ReactNode }) {
	const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
	const open = Boolean(anchorEl);

	const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
		setAnchorEl(event.currentTarget);
	};

	const handleClose = () => {
		setAnchorEl(null);
	};

	const [, setMemberState] = useRecoilState(memberInfoState);

	const logout = () => {
		localStorage.removeItem('accessToken');
		localStorage.removeItem('refreshToken');
		setMemberState(null);
		handleClose();
	};

	return (
		<NavbarLayout>
			<MainTabNavbarContainer>
				<button type="button" className="drop-down-menu" onClick={handleClick}>
					<h1>{tabName}</h1>
				</button>
				<Menu anchorEl={anchorEl} open={open} onClose={handleClose}>
					<MenuItem onClick={logout}>로그아웃</MenuItem>
				</Menu>
				<div className="nav-btn">{navBtn}</div>
			</MainTabNavbarContainer>
		</NavbarLayout>
	);
}

export default MainTabNavbar;
