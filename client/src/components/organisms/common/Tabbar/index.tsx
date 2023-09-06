import React, { useEffect, useState } from 'react';
import { TabbarItem, TabbarCenterItem } from 'components/atoms/common/TabbarItem';
import { useLocation } from 'react-router-dom';
import { TABBAR_RENDER_EXCEPTS } from 'constants/tabbarRenderExcepts';
import { TabBackground, TabContainer, TabWrapper } from './style';

function Tabbar() {
	const location = useLocation();
	const [isRender, setIsRender] = useState(true);

	useEffect(() => {
		if (TABBAR_RENDER_EXCEPTS.includes(location.pathname)) {
			setIsRender(false);
		} else {
			setIsRender(true);
		}
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
