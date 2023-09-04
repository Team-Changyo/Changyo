import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { GlobalStyles } from 'styles/GlobalStyles';
import { RecoilRoot } from 'recoil';
import { GlobalFonts } from 'styles/GlobalFonts';
import Tabbar from 'components/organisms/common/Tabbar';
import AppLayout from 'layouts/common/AppLayout';
import AccountPage from 'pages/AccountPage';
import QRPage from 'pages/QRPage';
import DepositPage from 'pages/DepositPage';

function AppRouter() {
	return (
		<RecoilRoot>
			<GlobalFonts />
			<GlobalStyles />
			<BrowserRouter>
				<AppLayout>
					<Routes>
						<Route path="/" element={<AccountPage />} />
						<Route path="/qr" element={<QRPage />} />
						<Route path="/deposit" element={<DepositPage />} />
					</Routes>
					<Tabbar />
				</AppLayout>
			</BrowserRouter>
		</RecoilRoot>
	);
}

export default AppRouter;
