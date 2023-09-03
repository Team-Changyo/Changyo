/* eslint-disable react/jsx-props-no-spreading */
import React, { useState } from 'react';
import { ReactComponent as HomeIconOn } from 'assets/icons/tabbar/home-icon-on.svg';
import { ReactComponent as HomeIconOff } from 'assets/icons/tabbar/home-icon-off.svg';
import { ReactComponent as QRIcon } from 'assets/icons/tabbar/qr-icon.svg';
import { ReactComponent as DepositIcon } from 'assets/icons/tabbar/deposit-icon.svg';
import { usePathMatch } from 'hooks/usePathMatch';
import { Link, useNavigate } from 'react-router-dom';
import { TabbarCenterItemWrapper, TabbarItemWrapper } from './style';

export function TabbarItem({ pathname }: { pathname: string }) {
	const $isActive = usePathMatch(pathname);

	const render = (() => {
		switch (pathname) {
			case '/qr':
				return (
					<Link to="/qr">
						<QRIcon />
						QR송금
					</Link>
				);
			case '/deposit':
				return (
					<Link to="/deposit">
						<DepositIcon />
						보증금관리
					</Link>
				);
			default:
				return <div />;
		}
	})();

	return <TabbarItemWrapper $isActive={$isActive}>{render}</TabbarItemWrapper>;
}

/**
 * 탭바의 CenterItem (홈버튼)
 */
export function TabbarCenterItem() {
	const isActive = usePathMatch('/');
	const [isHover, setIsHover] = useState(0);
	const navigate = useNavigate();
	const moveToHome = () => navigate('/');

	const hoverProp = {
		onMouseOver: () => setIsHover(1),
		onMouseOut: () => setIsHover(0),
	};

	const render =
		isActive || isHover ? (
			<HomeIconOn {...hoverProp} onClick={moveToHome} />
		) : (
			<HomeIconOff {...hoverProp} onClick={moveToHome} />
		);

	return <TabbarCenterItemWrapper>{render}</TabbarCenterItemWrapper>;
}
