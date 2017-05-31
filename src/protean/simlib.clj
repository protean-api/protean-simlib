;; OUT OF THE BOX EXAMPLE SIM LIBRARY
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns protean.simlib
  (:require [protean.api.transformation.sim :refer :all]
            [protean.api.protocol.http :as h]
            [protean.api.transformation.coerce :refer [clj js]]))


;; =============================================================================
;; Generally Useful Functions
;; =============================================================================

(defn parse-id [s] (Integer. (re-find  #"\d+" s)))

(defmacro prob
  "First arity evaluates the provided fn with specified probability.
   Second arity evaulates first fn with provided prob else second fn."
  ([n then] `(if (< (rand) ~n) ~then))
  ([n then else] `(if (< (rand) ~n) ~then ~else)))

;; =============================================================================
;; Sim Library Request Functions
;; =============================================================================

(defn bp [p] (body-param p true))

(defn qp [p] (query-param p))

(defn qp= [x p] (= (qp p) x))

(defn pp [p] (path-param p))

(defn mp [p key] ((matrix-params p) key))

;; =============================================================================
;; Sim Library Response Functions
;; =============================================================================

(defn h-rsp [s hdr] {:status s :headers {h/loc hdr}})

(defn b-rsp [s h b] {:status s :headers h :body b})

(defn rsp [s] {:status s})

(defn jsn
  ([s b] (b-rsp s {h/ctype h/jsn} (js b)))
  ([s h b] (b-rsp s (merge h {h/ctype h/jsn}) (js b))))

(defn txt [s b] (b-rsp s {h/ctype h/txt} b))

;; =============================================================================
;; Sim Library Scenario Modelling and Route Solution
;; =============================================================================

(defn solve [routes]
  (seq (remove nil? (map #(if ((first %)) (last %) nil) routes))))

(defn route-rsp [routes]
  (if-let [errs (solve routes)] ((rand-nth errs)) (success)))
