module.exports = {
    pwa: {
        name: 'Assistant',
        themeColor: '#030717',
        msTileColor: '#000000',
        appleMobileWebAppCapable: 'no',
        appleMobileWebAppStatusBarStyle: 'black',

        // configure the workbox plugin
        workboxPluginMode: 'GenerateSW',
        workboxOptions: {
            include: [/\.html$/, /\.css$/,/\.js/],
            navigateFallback: '/login',
            templatedUrls: {
                '/login': '@/public/index.html',
                '/register': '@/public/index.html'
            },
            runtimeCaching: [
                {
                    urlPattern: new RegExp('^https://stackpath\.bootstrapcdn\.com/bootstrap/.+bootstrap.min.css'),
                    handler: 'cacheFirst',
                    options: {
                        cacheableResponse: {
                           statuses: [200]
                        }
                    }
                }
            ]
        }
    }
};