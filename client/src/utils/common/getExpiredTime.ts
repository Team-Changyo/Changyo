export const getExpiredTime = (minute: number) => {
	return Date.now() + minute * 60 * 1000;
};
