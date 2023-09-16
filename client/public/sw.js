// sw.js

// install event
self.addEventListener('install', (event) => {
	console.log('[Service Worker] installed', event);
});

// activate event
self.addEventListener('activate', () => {
	// console.log('[Service Worker] actived');
});

// fetch event
self.addEventListener('fetch', () => {
	// console.log('[Service Worker] fetched resource ' + e.request.url);
});
