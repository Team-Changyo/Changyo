import axios from 'axios';

const baseURL = import.meta.env.VITE_SERVER_BASE_URL;

export const instance = axios.create({
	baseURL,
	headers: {
		'Content-Type': 'application/json',
	},
});
