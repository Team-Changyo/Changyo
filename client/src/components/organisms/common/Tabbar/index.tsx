import React from 'react';
import { TabbarItem, TabbarCenterItem } from 'components/atoms/common/TabbarItem';
import { TabBackground, TabContainer, TabWrapper } from './style';

function Tabbar() {
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
