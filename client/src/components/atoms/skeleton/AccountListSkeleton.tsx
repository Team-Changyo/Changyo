import React from 'react';
import ContentLoader from 'react-content-loader';

function AccountListSkeleton() {
	return (
		<ContentLoader
			speed={1}
			width={250}
			height={64}
			viewBox="0 0 250 64"
			backgroundColor="#f3f3f3"
			foregroundColor="#ecebeb"
		>
			<rect x="140" y="33" rx="0" ry="0" width="0" height="1" />
			<rect x="66" y="35" rx="0" ry="0" width="1" height="0" />
			<rect x="50" y="48" rx="3" ry="3" width="160" height="16" />
			<rect x="50" y="0" rx="3" ry="3" width="180" height="16" />
			<circle cx="24" cy="30" r="20" />
			<rect x="50" y="24" rx="3" ry="3" width="200" height="16" />
		</ContentLoader>
	);
}

export default AccountListSkeleton;
