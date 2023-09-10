import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import userState from 'store/user';

function PrivateRoute() {
	const [user] = useRecoilState(userState);
	return user ? <Outlet /> : <Navigate to="/auth/login" />;
}
export default PrivateRoute;
