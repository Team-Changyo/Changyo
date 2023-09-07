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
import CreateQRPage from 'pages/CreateQRPage';
import ViewQRPageNormal from 'pages/ViewNormalQRPage';
import ViewQRPageDeposit from 'pages/ViewDepositQRPage';
import RemittanceNormalPage from 'pages/RemittanceNormalPage';
import RemittanceDepositPage from 'pages/RemittanceDepositPage';

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
						<Route path="/qr/normal" element={<ViewQRPageNormal />} />
						<Route path="/qr/deposit/:qid" element={<ViewQRPageDeposit />} />
						<Route path="/qr/create" element={<CreateQRPage />} />
						<Route path="/deposit" element={<DepositPage />} />
						<Route path="/remittance/normal" element={<RemittanceNormalPage />} />
						<Route path="/remittance/deposit" element={<RemittanceDepositPage />} />
					</Routes>
					<Tabbar />
				</AppLayout>
			</BrowserRouter>
		</RecoilRoot>
	);
}

export default AppRouter;
