import React from 'react';
import ContentLoader from 'react-content-loader';

function LargeMoneySkeleton() {
	return (
		<ContentLoader
			speed={1}
			width={300}
			height={48}
			viewBox="0 0 300 48"
			backgroundColor="#f3f3f3"
			foregroundColor="#ecebeb"
		>
			<rect x="140" y="33" rx="0" ry="0" width="0" height="1" />
			<rect x="66" y="35" rx="0" ry="0" width="1" height="0" />
			<rect x="5" y="19" rx="0" ry="0" width="300" height="30" />
			<rect x="4" y="1" rx="0" ry="0" width="150" height="14" />
		</ContentLoader>
	);
}

export default LargeMoneySkeleton;
