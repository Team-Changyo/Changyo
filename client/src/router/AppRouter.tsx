import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AccountPage from 'pages/AccountPage';
import Tabbar from 'components/organisms/common/Tabbar';
import AppLayout from 'layouts/common/AppLayout';
import { GlobalStyles } from 'styles/GlobalStyles';
import { RecoilRoot } from 'recoil';
import Navbar from 'components/organisms/common/Navbar';
import { GlobalFonts } from 'styles/GlobalFonts';

function AppRouter() {
	return (
		<RecoilRoot>
			<GlobalFonts />
			<GlobalStyles />
			<BrowserRouter>
				<AppLayout>
					<Navbar />
					<Routes>
						<Route path="/" element={<AccountPage />} />
						<Route path="/qr" element={<AccountPage />} />
						<Route path="/deposit" element={<AccountPage />} />
					</Routes>
					<Tabbar />
				</AppLayout>
			</BrowserRouter>
		</RecoilRoot>
	);
}

export default AppRouter;
