# Input Validation (Code Review, Lesson 3.2)

Run:

```
java InputValidationDemo.java
```

What to look for:

* Range: `quantity=-5` has the right type and still produces a negative total.
* Allowlist: the sort key is looked up, so request text never becomes a column name.
* Path: `../secret.txt` leaves the invoice folder until the normalized path is checked.

The review question: what is the strangest value that still passes this code?
