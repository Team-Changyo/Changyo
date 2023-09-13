import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { memberInfoState } from 'store/member';

function PrivateRoute() {
	const [memberInfo] = useRecoilState(memberInfoState);
	return memberInfo ? <Outlet /> : <Navigate to="/auth/login" />;
}
export default PrivateRoute;
