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
import { GlobalKeyFrames } from 'styles/GlobalKeyFrames';
import SettlementDetail from 'pages/deposit/SettlementDetail';
import AccountRegisterPage from 'pages/account/AccountRegisterPage';
import SuccessPage from 'pages/etc/SuccessPage';
import FailPage from 'pages/etc/FailPage';
import { Toaster, toast } from 'react-hot-toast';
import { memberInfoState } from 'store/member';
import { isAxiosError } from 'axios';
import { findMemberInfo } from 'utils/apis/auth';
import PageNotFound from 'pages/etc/PageNotFound';
import PrivateRoute from './PrivateRoute';

function AppRouter() {
	const [isLoading, setIsLoading] = useState(true);
	const [memberInfo, setMemberInfo] = useRecoilState(memberInfoState);

	const loading = () => {
		setTimeout(() => {
			setIsLoading(false);
		}, 1500);
	};

	const fetchUserData = async () => {
		const accessToken = localStorage.getItem('accessToken');

		if (accessToken) {
			try {
				const response = await findMemberInfo();

				if (response.status === 200) {
					setMemberInfo(response.data.data);
					toast.success('로그인 되었습니다.');
				}
			} catch (error) {
				console.error(error);
				if (isAxiosError(error)) {
					toast.error(error.response?.data.message);
				}
			}
		}
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
								<Route path="/" element={<Navigate replace to={memberInfo ? '/account' : '/auth/login'} />} />
								<Route path="/auth/login" element={<LoginPage />} />
								<Route path="/auth/register" element={<RegisterPage />} />

								<Route path="/" element={<PrivateRoute />}>
									<Route path="/account" element={<AccountPage />} />
									<Route path="/account/:accountId" element={<AccountDetailPage />} />
									<Route path="/account/register" element={<AccountRegisterPage />} />
									<Route path="/qr" element={<QRPage />} />
									<Route path="/qr/normal" element={<ViewQRPageNormal />} />
									<Route path="/qr/deposit/:qrCodeId" element={<ViewQRPageDeposit />} />
									<Route path="/qr/create" element={<CreateQRPage />} />
									<Route path="/deposit" element={<DepositPage />} />
									<Route path="/deposit/settlement/:sid" element={<SettlementDetail />} />
									<Route path="/remittance/normal" element={<RemittanceNormalPage />} />
									<Route path="/remittance/deposit" element={<RemittanceDepositPage />} />

									<Route path="/success" element={<SuccessPage />} />
									<Route path="/fail" element={<FailPage />} />
								</Route>
								<Route path="/*" element={<PageNotFound />} />
							</Routes>
							<Tabbar />
						</>
					)}
				</AppLayout>
			</BrowserRouter>
			<Toaster />
		</div>
	);
}

export default AppRouter;
