import { useState } from 'react';

export const useAuth = () => {
	const [test] = useState('');
	return test;
};
