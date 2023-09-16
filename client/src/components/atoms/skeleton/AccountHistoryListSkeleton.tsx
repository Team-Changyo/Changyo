import React from 'react';
import ContentLoader from 'react-content-loader';

function AccountHistoryListSkeleton() {
	return (
		<ContentLoader
			speed={1}
			width={300}
			height={228}
			viewBox="0 0 300 228"
			backgroundColor="#f3f3f3"
			foregroundColor="#ecebeb"
		>
			<rect x="140" y="33" rx="0" ry="0" width="0" height="1" />
			<rect x="66" y="35" rx="0" ry="0" width="1" height="0" />
			<rect x="50" y="64" rx="3" ry="3" width="150" height="16" />
			<circle cx="24" cy="84" r="20" />
			<rect x="50" y="88" rx="3" ry="3" width="90" height="16" />
			<rect x="0" y="32" rx="3" ry="3" width="150" height="16" />
			<rect x="50" y="122" rx="3" ry="3" width="150" height="16" />
			<circle cx="24" cy="142" r="20" />
			<rect x="50" y="146" rx="3" ry="3" width="90" height="16" />
			<rect x="50" y="180" rx="3" ry="3" width="150" height="16" />
			<circle cx="24" cy="200" r="20" />
			<rect x="50" y="204" rx="3" ry="3" width="90" height="16" />
		</ContentLoader>
	);
}

export default AccountHistoryListSkeleton;
