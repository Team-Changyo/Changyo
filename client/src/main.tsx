import React from 'react';
import ReactDOM from 'react-dom/client';
import { RecoilRoot } from 'recoil';
import AppRouter from 'router/AppRouter';

const Render = (
	<RecoilRoot>
		<AppRouter />
	</RecoilRoot>
);

ReactDOM.createRoot(document.getElementById('root')!).render(Render);
