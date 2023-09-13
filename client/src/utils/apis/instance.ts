import axios, { InternalAxiosRequestConfig } from 'axios';

const baseURL = import.meta.env.VITE_SERVER_BASE_URL;

const instance = axios.create({
	baseURL,
	headers: {
		'Content-Type': 'application/json',
	},
});

// accessToken 인터셉터
instance.interceptors.request.use((config: InternalAxiosRequestConfig): InternalAxiosRequestConfig => {
	const token = localStorage.getItem('accessToken');

	if (token) config.headers.Authorization = `Bearer ${token}`;

	return config;
});

export default instance;
