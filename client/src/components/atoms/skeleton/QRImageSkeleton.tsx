import React from 'react';
import ContentLoader from 'react-content-loader';

function QRImageSkeleton() {
	return (
		<ContentLoader
			speed={1}
			width={300}
			height={300}
			viewBox="0 0 300 300"
			backgroundColor="#f3f3f3"
			foregroundColor="#ecebeb"
		>
			<rect x="0" y="0" rx="3" ry="3" width="300" height="300" />
			<rect x="51" y="97" rx="0" ry="0" width="0" height="1" />
		</ContentLoader>
	);
}

export default QRImageSkeleton;
