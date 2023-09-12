// imgs
declare module '*.jpg';
declare module '*.png';
declare module '*.jpeg';
declare module '*.gif';
declare module '*.svg' {
	import React = require('react');

	export const ReactComponent: React.FC<React.SVGProps<SVGSVGElement>>;
	const src: string;
	export default src;
}

// font
declare module '*.otf';

// env
interface ImportMetaEnv {
	readonly VITE_SERVER_BASE_URL: string;
}

interface ImportMeta {
	readonly env: ImportMetaEnv;
}
