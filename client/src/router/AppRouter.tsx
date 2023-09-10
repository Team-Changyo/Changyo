import React, { useEffect, useState } from 'react';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import { GlobalStyles } from 'styles/GlobalStyles';
import { useRecoilState } from 'recoil';
import { GlobalFonts } from 'styles/GlobalFonts';
import Tabbar from 'components/organisms/common/Tabbar';
import AppLayout from 'layouts/common/AppLayout';
import AccountPage from 'pages/account/AccountPage';
import QRPage from 'pages/qr/QRPage';
import DepositPage from 'pages/deposit/DepositPage';
import CreateQRPage from 'pages/qr/CreateQRPage';
import ViewQRPageNormal from 'pages/qr/ViewNormalQRPage';
import ViewQRPageDeposit from 'pages/qr/ViewDepositQRPage';
import RemittanceNormalPage from 'pages/remit/RemittanceNormalPage';
import RemittanceDepositPage from 'pages/remit/RemittanceDepositPage';
import AccountDetailPage from 'pages/account/AccountDetailPage';
import LoadingPage from 'pages/etc/LoadingPage';
import LoginPage from 'pages/auth/LoginPage';
import RegisterPage from 'pages/auth/RegisterPage';
import userState from 'store/user';
import { GlobalKeyFrames } from 'styles/GlobalKeyFrames';
import SettlementDetail from 'pages/deposit/SettlementDetail';
import AccountRegisterPage from 'pages/account/AccountRegisterPage';
import SuccessPage from 'pages/etc/SuccessPage';
import FailPage from 'pages/etc/FailPage';
import PrivateRoute from './PrivateRoute';

function AppRouter() {
	const [isLoading, setIsLoading] = useState(true);
	const [user, setUser] = useRecoilState(userState);

	const loading = () => {
		setTimeout(() => {
			setIsLoading(false);
		}, 1500);
	};

	const fetchUserData = () => {
		// TODO API 나오면 로직 추가
		setUser({ memberId: 1, name: '전인혁' });
		// setUser(null);
	};

	useEffect(() => {
		loading();
		fetchUserData();
	}, []);

	return (
		<div className="container">
			<GlobalFonts />
			<GlobalStyles />
			<GlobalKeyFrames />
			<BrowserRouter>
				<AppLayout>
					{/* Loading */}
					{isLoading ? (
						<LoadingPage />
					) : (
						<>
							<Routes>
								<Route path="/" element={<Navigate replace to={user ? '/account' : '/auth/login'} />} />
								<Route path="/auth/login" element={<LoginPage />} />
								<Route path="/auth/register" element={<RegisterPage />} />

								<Route path="/" element={<PrivateRoute />}>
									<Route path="/account" element={<AccountPage />} />
									<Route path="/account/:aid" element={<AccountDetailPage />} />
									<Route path="/account/register" element={<AccountRegisterPage />} />
									<Route path="/qr" element={<QRPage />} />
									<Route path="/qr/normal" element={<ViewQRPageNormal />} />
									<Route path="/qr/deposit/:qid" element={<ViewQRPageDeposit />} />
									<Route path="/qr/create" element={<CreateQRPage />} />
									<Route path="/deposit" element={<DepositPage />} />
									<Route path="/deposit/settlement/:sid" element={<SettlementDetail />} />
									<Route path="/remittance/normal" element={<RemittanceNormalPage />} />
									<Route path="/remittance/deposit" element={<RemittanceDepositPage />} />

									<Route path="/success" element={<SuccessPage />} />
									<Route path="/fail" element={<FailPage />} />
								</Route>
							</Routes>
							<Tabbar />
						</>
					)}
				</AppLayout>
			</BrowserRouter>
		</div>
	);
}

export default AppRouter;
