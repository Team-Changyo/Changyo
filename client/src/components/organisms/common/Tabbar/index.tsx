import React, { useEffect, useState } from 'react';
import { TabbarItem, TabbarCenterItem } from 'components/atoms/common/TabbarItem';
import { useLocation } from 'react-router-dom';
import { TABBAR_RENDER_EXCEPTS } from 'constants/tabbarRenderExcepts';
import { TabBackground, TabContainer, TabWrapper } from './style';

function Tabbar() {
	const location = useLocation();
	const [isRender, setIsRender] = useState(true);

	useEffect(() => {
		const isPathExcluded = TABBAR_RENDER_EXCEPTS.some((path) => {
			if (typeof path === 'string') {
				return path === location.pathname;
			}
			if (path instanceof RegExp) {
				return path.test(location.pathname);
			}
			return false;
		});
		setIsRender(!isPathExcluded);
	}, [location, isRender]);

	if (isRender)
		return (
			<TabWrapper>
				<TabContainer>
					<TabbarItem pathname="/qr" />
					<TabbarCenterItem />
					<TabbarItem pathname="/deposit" />
					<TabBackground>
						<div id="box" />
					</TabBackground>
				</TabContainer>
			</TabWrapper>
		);
}

export default Tabbar;
