(ns ring-app.core
  (:require [reitit.ring :as reitit]
            [muuntaja.middleware :as muuntaja]
            [ring.adapter.jetty :as jetty]
            [ring.util.http-response :as response]
            [ring.middleware.reload :refer [wrap-reload]]))

;; Comments

(defn html-handler [request-map]
  (response/ok
    (str "<html><body> your IP is: "
      (:remote-addr request-map)
    "</body></html>")))

(defn wrap-formats [handler]
  (-> handler (muuntaja/wrap-format)))

(def routes
  [["/" {:get html-handler :post html-handler}]
   ["/echo/:id" {:get (fn [{{:keys [id]} :path-params}] (response/ok (str "<p>the value is: " id "</p>")))}]
   ["/api" {:middleware [wrap-formats]} 
     ["/multiply" {:post (fn [{{:keys [a b]} :body-params}] (response/ok {:result (* a b)}))}]]])

(def handler
  (reitit/ring-handler
   (reitit/router routes)))



;;(defn json-handler [request]
;;  (response/ok {:result (get-in request [:body-params :id])}))

;;(def handler json-handler)

(defn wrap-nocache [handler]
  (fn [request]
    (-> request
        handler
        (assoc-in [:headers "Pragma"] "no-cache"))))


(defn -main []
  (jetty/run-jetty
   (-> #'handler
       wrap-nocache
       wrap-reload)
   {:port 3000
    :join? false}))
