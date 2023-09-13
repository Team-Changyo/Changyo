import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { authState } from 'store/user';

function PrivateRoute() {
	const [auth] = useRecoilState(authState);
	return auth ? <Outlet /> : <Navigate to="/auth/login" />;
}
export default PrivateRoute;
