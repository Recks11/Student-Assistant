/**
 * Welcome to your Workbox-powered service worker!
 *
 * You'll need to register this file in your web app and you should
 * disable HTTP caching for this file too.
 * See https://goo.gl/nhQhGp
 *
 * The rest of the code is auto-generated. Please don't update this file
 * directly; instead, make changes to your Workbox build configuration
 * and re-run your build process.
 * See https://goo.gl/2aRDsh
 */

importScripts("https://storage.googleapis.com/workbox-cdn/releases/3.2.0/workbox-sw.js");

importScripts(
  "/precache-manifest.be1a92161442f46213031b8884fdf67a.js"
);

workbox.core.setCacheNameDetails({prefix: "student-assistant-pwa"});

/**
 * The workboxSW.precacheAndRoute() method efficiently caches and responds to
 * requests for URLs in the manifest.
 * See https://goo.gl/S9QRab
 */
self.__precacheManifest = [
  {
    "url": "/login",
    "revision": "de97e474d77ffcba7d79f1ddfd9b310d"
  },
  {
    "url": "/register",
    "revision": "de97e474d77ffcba7d79f1ddfd9b310d"
  }
].concat(self.__precacheManifest || []);
workbox.precaching.suppressWarnings();
workbox.precaching.precacheAndRoute(self.__precacheManifest, {});

workbox.routing.registerNavigationRoute("/login");

workbox.routing.registerRoute(/^https:\/\/stackpath.bootstrapcdn.com\/bootstrap\/.+bootstrap.min.css/, workbox.strategies.cacheFirst({ plugins: [new workbox.cacheableResponse.Plugin({"statuses":[200]})] }), 'GET');
