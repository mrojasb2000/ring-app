(ns ring-app.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]))

(defn formatString  [label key map]
  (str  "<p><b>" label ": </b>"
    (key map)
    "</p>"))

(defn handler [request-map]
  (response/response
    (str "<html><body> Request data: "
        (formatString "server port" :server-port request-map)
        (formatString "server name" :server-name request-map)
        (formatString "remote address" :remote-addr request-map)
        (formatString "query string" :query-string request-map)
        (formatString "scheme" :scheme request-map)
        (formatString "request method" :request-method  request-map)
        (formatString "content type" :content-type  request-map)
        (formatString "content-length" :content-length  request-map)
        (formatString "character endcoding" :character-endcoding  request-map)
        (formatString "headers" :headers  request-map)
        (formatString "context" :context  request-map)
        (formatString "uri" :uri  request-map)
        (formatString "ssl-client-cert" :ssl-client-cert  request-map)
        "</p></body></html>")))

(defn wrap-nocache [handler]
  (fn [request]
    (-> request
        handler
        (assoc-in [:headers "Pragma"] "no-cache"))))

(defn -main []
  (jetty/run-jetty
   (-> handler wrap-nocache)
   {:port 3000
    :join? false}))
