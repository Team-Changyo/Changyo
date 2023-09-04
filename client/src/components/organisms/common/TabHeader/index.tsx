import React from 'react';
import { TabHeaderContainer } from './style';

function TabHeader({ tabName }: { tabName: string }) {
	return (
		<TabHeaderContainer>
			<h1>{tabName}</h1>
		</TabHeaderContainer>
	);
}

export default TabHeader;
