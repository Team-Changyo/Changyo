import { TABBAR_RENDER_EXCEPTS } from 'constants/tabbarRenderExcepts';
import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

const useTabbarRender = () => {
	const location = useLocation();
	const [isRender, setIsRender] = useState(false);

	useEffect(() => {
		const isPathExcluded = TABBAR_RENDER_EXCEPTS.some((path) => {
			if (typeof path === 'string') {
				return path === location.pathname;
			}
			if (path instanceof RegExp) {
				return path.test(location.pathname);
			}
			return false;
		});
		setIsRender(!isPathExcluded);
	}, [location, isRender]);

	return isRender;
};

export default useTabbarRender;
