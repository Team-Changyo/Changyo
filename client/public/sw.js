// install event
self.addEventListener('install', (_e) => {
	console.log('[Service Worker] installed');
});

// activate event
self.addEventListener('activate', (_e) => {
	console.log('[Service Worker] actived', e);
});

// fetch event
self.addEventListener('fetch', (_e) => {
	console.log('[Service Worker] fetched resource ' + e.request.url);
});
