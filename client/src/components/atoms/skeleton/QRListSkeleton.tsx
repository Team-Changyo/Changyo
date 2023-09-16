import React from 'react';
import ContentLoader from 'react-content-loader';

function QRListSkeleton() {
	return (
		<ContentLoader
			speed={1}
			width={300}
			height={240}
			viewBox="0 0 300 240"
			backgroundColor="#f3f3f3"
			foregroundColor="#ecebeb"
		>
			<rect x="140" y="33" rx="0" ry="0" width="0" height="1" />
			<rect x="66" y="35" rx="0" ry="0" width="1" height="0" />
			<rect x="50" y="104" rx="3" ry="3" width="70" height="16" />
			<circle cx="24" cy="134" r="20" />
			<rect x="50" y="125" rx="3" ry="3" width="200" height="16" />
			<rect x="0" y="0" rx="3" ry="3" width="150" height="16" />
			<rect x="50" y="146" rx="3" ry="3" width="150" height="16" />
			<rect x="50" y="30" rx="3" ry="3" width="70" height="16" />
			<circle cx="24" cy="60" r="20" />
			<rect x="50" y="51" rx="3" ry="3" width="200" height="16" />
			<rect x="50" y="72" rx="3" ry="3" width="150" height="16" />
			<rect x="50" y="178" rx="3" ry="3" width="70" height="16" />
			<circle cx="24" cy="208" r="20" />
			<rect x="50" y="199" rx="3" ry="3" width="200" height="16" />
			<rect x="50" y="220" rx="3" ry="3" width="150" height="16" />
		</ContentLoader>
	);
}

export default QRListSkeleton;
