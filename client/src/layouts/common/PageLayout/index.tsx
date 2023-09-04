import React, { ReactNode } from 'react';
import { PageLayoutContainer } from './style';

function PageLayout({ children }: { children: ReactNode }) {
	return <PageLayoutContainer>{children}</PageLayoutContainer>;
}

export default PageLayout;
