import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AccountPage from 'pages/AccountPage';

function AppRouter() {
	return (
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<AccountPage />} />
			</Routes>
		</BrowserRouter>
	);
}

export default AppRouter;
