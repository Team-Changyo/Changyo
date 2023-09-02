import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

/**
 * 현재 url 경로가 pathmame과 일치하는지에 따라 true/false 반환
 * @param pathname
 * @returns true/false
 */
export const usePathMatch = (pathname: string) => {
	const location = useLocation();
	const [isActive, setIsActive] = useState(false);

	useEffect(() => {
		if (location.pathname === pathname) {
			setIsActive(true);
		} else {
			setIsActive(false);
		}
	}, [location.pathname, pathname]);

	return isActive;
};
