import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import eslint from 'vite-plugin-eslint';
import tsconfigPaths from 'vite-tsconfig-paths';
import svgr from 'vite-plugin-svgr';
import mkcert from 'vite-plugin-mkcert';
import { VitePWA } from 'vite-plugin-pwa';

// https://vitejs.dev/config/
export default defineConfig({
	base: '/',
	plugins: [
		react(),
		eslint(),
		tsconfigPaths(),
		svgr(),
		mkcert(),
		VitePWA({
			injectRegister: 'auto',
		}),
	],
	resolve: {
		extensions: ['.js', '.ts', '.tsx', '.jsx'],
	},
});
