import React, { useEffect, useState } from 'react';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import { GlobalStyles } from 'styles/GlobalStyles';
import { useRecoilState } from 'recoil';
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
import AccountDetailPage from 'pages/AccountDetailPage';
import LoadingPage from 'pages/LoadingPage';
import LoginPage from 'pages/LoginPage';
import RegisterPage from 'pages/RegisterPage';
import userState from 'store/user';
import { GlobalKeyFrames } from 'styles/GlobalKeyFrames';
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
		setUser(null);
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
									<Route path="/qr" element={<QRPage />} />
									<Route path="/qr/normal" element={<ViewQRPageNormal />} />
									<Route path="/qr/deposit/:qid" element={<ViewQRPageDeposit />} />
									<Route path="/qr/create" element={<CreateQRPage />} />
									<Route path="/deposit" element={<DepositPage />} />
									<Route path="/remittance/normal" element={<RemittanceNormalPage />} />
									<Route path="/remittance/deposit" element={<RemittanceDepositPage />} />
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
