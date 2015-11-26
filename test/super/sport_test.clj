(ns super.sport-test
  (:require [super.sport :refer :all]
            [clojure.test :refer :all]))

(deftest bump-version-should-work
  (are [exp given] (= exp (apply bump-version given))
       "1"          ["1" true]
       "1"          ["1-SNAPSHOT" true]
       "2-SNAPSHOT" ["1"]
       "8675309"    ["8675309-SNAPSHOT" true]
       "2-SNAPSHOT" ["1-SNAPSHOT"]
       "1"          ["1-SNAPSHOT" "release"])
  (is (thrown? IllegalArgumentException
        (bump-version "1.2.3"))))
