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
;; Sim Library Request Param Functions offer shorthand notation
;; =============================================================================

(defn bp [req p] (body-param req p true))

(defn qp [req p] (query-param req p))

(defn qp= [req x p] (= (qp req p) x))

(defn pp [req p] (path-param req p))

(defn mp [req p key] ((matrix-params req p) key))

;; =============================================================================
;; Sim Library Response Functions are not built from codex specification
;; anything goes :=)
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

; (defn solve [routes]
;   (seq (remove nil? (map #(if ((first %)) (last %) nil) routes))))
;
; (defn route-rsp [routes]
;   (if-let [errs (solve routes)] ((rand-nth errs)) (success)))
