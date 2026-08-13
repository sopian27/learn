---
name: tdd-workflow
description: "Use when implementing a feature or fix that has a test (existing or new) and the RED phase has not been explicitly verified yet."
---

## Structure Template
1. RED harus benar-benar dijalankan
2. failure harus diamati
3. implementation minimum
4. GREEN harus dijalankan
5. REFACTOR setelah green

## Existing Tests

When tests already exist, do not assume they represent a valid RED phase.
Before implementing the requested behavior, run the relevant test and identify
the specific behavior that is missing or incorrect.

If the test already passes before implementation, do not claim TDD RED-GREEN
was followed. First identify whether the implementation is already complete;
if it is complete, report that no implementation is needed rather than
pretending a TDD cycle occurred.


