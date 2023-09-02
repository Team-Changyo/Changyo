import React, { ReactNode } from 'react';
import { AppLayoutContainer } from './style';

function AppLayout({ children }: { children: ReactNode }) {
	return <AppLayoutContainer>{children}</AppLayoutContainer>;
}

export default AppLayout;
