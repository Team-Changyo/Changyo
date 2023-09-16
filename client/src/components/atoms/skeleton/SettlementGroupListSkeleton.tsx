import React from 'react';
import ContentLoader from 'react-content-loader';

function SettlementGroupListSkeleton() {
	return (
		<ContentLoader
			speed={1}
			width={300}
			height={240}
			viewBox="0 0 300 240"
			backgroundColor="#f3f3f3"
			foregroundColor="#ecebeb"
		>
			<rect x="100" y="33" rx="0" ry="0" width="0" height="1" />
			<rect x="26" y="35" rx="0" ry="0" width="1" height="0" />
			<rect x="10" y="125" rx="3" ry="3" width="100" height="16" />
			<rect x="0" y="0" rx="3" ry="3" width="150" height="16" />
			<rect x="10" y="146" rx="3" ry="3" width="100" height="16" />
			<rect x="10" y="30" rx="3" ry="3" width="200" height="16" />
			<rect x="10" y="51" rx="3" ry="3" width="100" height="16" />
			<rect x="10" y="72" rx="3" ry="3" width="100" height="16" />
			<rect x="10" y="178" rx="3" ry="3" width="200" height="16" />
			<rect x="10" y="199" rx="3" ry="3" width="100" height="16" />
			<rect x="11" y="219" rx="3" ry="3" width="100" height="16" />
			<rect x="10" y="104" rx="3" ry="3" width="200" height="16" />
		</ContentLoader>
	);
}

export default SettlementGroupListSkeleton;
