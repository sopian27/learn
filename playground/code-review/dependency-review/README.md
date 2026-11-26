# Dependency Review (Code Review, Lesson 3.4)

Run:

```
java DependencyDiffDemo.java
```

What to look for:

* Transitive: the PR adds one library, but two new artifacts ship.
* Downgrade: an existing library moves to an older version without any line about it in the diff.
* Floating: a version range can resolve to different code on the next build.

All artifacts and versions are made up. The version change is written into the demo data, not resolved by Maven. In a real project, compare `mvn dependency:tree` output before and after.

The review question: what does this one line actually bring in?
