(ns super.sport)

(defn parse-single-segment-version
  [version]
  (if-let [[_ v] (re-find #"^(\d+)(-SNAPSHOT)?$" version)]
    (read-string v)
    (throw (IllegalArgumentException.
             (format "%s is not a single-segment version")))))

(defn new-version
  [version release?]
  (if release?
    (str version)
    (str (inc version) "-SNAPSHOT")))

(defn bump-version
  "Gives the next logical single-segment version.

  Follows the following rules:
  1. the version is parsed, and any -SNAPSHOT suffix is dropped
  2. if release? is true, the version is returned
  3. otherwise, the version is incremented by one, and a -SNAPSHOT
     suffix is appended"
  [version-str & [release?]]
  (-> version-str
      parse-single-segment-version
      (new-version release?)))
