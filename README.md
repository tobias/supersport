# Super Sport

An almost worthless utility library that aids with releasing projects that have
single-segment version numbers.

## Usage

This doesn't provide any leiningen tasks or middleware, but needs to
be a plugin dependency to be available to the change task. So, add
`[supersport "1"]` to the `:plugins` vector of you project.clj, then
update the release tasks to be:

```clojure
:release-tasks [["vcs" "assert-committed"]
                  ["change" "version"
                   "super.sport/bump-version" "release"] ; <- this line changed
                  ["vcs" "commit"]
                  ["vcs" "tag"]
                  ["deploy"]
                  ["change" "version" "super.sport/bump-version"] ; <- this line changed
                  ["vcs" "commit"]
                  ["vcs" "push"]]
```

For more details on the release tasks, see the lein
[deploy guide](https://github.com/technomancy/leiningen/blob/stable/doc/DEPLOY.md#releasing-simplified).

## What it does

This provides one function: `super.sport/bump-version` that mimics
`leiningen.release/bump-version`, but operates on single-segment
versions instead of semantic versions. A single-segment version will
match `#"^\d+(-SNAPSHOT)?$"`. Examples include: `"1"`, `99-SNAPSHOT"`,
`"8675309"`.

The function follows the following rules:

  1. the version is parsed, and any -SNAPSHOT suffix is dropped
  2. if a release version was requested, the version is returned
  3. otherwise, the version is incremented by one, and a -SNAPSHOT
     suffix is appended

## Why?

There are some classes of applications where semantic versioning
doesn't make sense, and a single incrementing version will
suffice (a web app is a good example).

## Thanks

Thanks to [Nelson Morris](https://github.com/xeqi/) for the inspiration.

## License

Copyright © 2015 Tobias Crawley

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
