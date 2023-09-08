import React, { ReactNode, useEffect, useState } from 'react';
import useTabbarRender from 'hooks/useTabbarRender';
import { PageLayoutContainer } from './style';

function PageLayout({ children }: { children: ReactNode }) {
	const isRender = useTabbarRender();
	const [paddingBottom, setPaddingBottom] = useState('0px');

	/* 탭바 Render 여부에 따라,  */
	useEffect(() => {
		if (isRender) setPaddingBottom('56px');
		else setPaddingBottom('0px');
	}, [isRender]);

	return <PageLayoutContainer $paddingBottom={paddingBottom}>{children}</PageLayoutContainer>;
}

export default PageLayout;
