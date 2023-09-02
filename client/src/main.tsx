import AppLayout from 'layouts/common/AppLayout';
import React from 'react';
import ReactDOM from 'react-dom/client';
import { RecoilRoot } from 'recoil';
import AppRouter from 'router/AppRouter';
import { GlobalStyles } from 'styles/GlobalStyles';

const Render = (
	<AppLayout>
		<RecoilRoot>
			<GlobalStyles />
			<AppRouter />
		</RecoilRoot>
	</AppLayout>
);

ReactDOM.createRoot(document.getElementById('root')!).render(Render);
