---
name: harden-agent-mistakes
description: "Use when the agent (or the user) repeats the same mistake, the same code-review comment appears twice, or the user says 'kok ngulang lagi' — turns the mistake into a hard rule (hook, lint, CI, test) instead of another line in CLAUDE.md."
---

Idea from Lauren Tan's talk: soft rules (CLAUDE.md, skills, reminders) get forgotten. Hard rules (compiler, lint, CI, hook, architecture) cannot. Same comment twice = code smell.

## Steps

1. **Name the failure.** One sentence: what went wrong, where, how often. Cite the evidence (file, transcript line, review comment). No evidence = do not proceed; ask.
2. **Pick the hardest layer that fits.** Prefer top of list:
   1. Make the bad path impossible (types, structure, delete the option).
   2. Compiler / static analysis / lint rule (e.g. Checkstyle, ArchUnit, ESLint `no-restricted-syntax`).
   3. Test that fails (unit, ArchUnit architecture test, Testcontainers integration).
   4. Hook (`PreToolUse` / `PostToolUse` in `.claude/settings.json`) or CI check.
   5. Skill / CLAUDE.md line. Last resort, only if 1-4 are impossible. Say why.
3. **Write the rule.** Smallest change that blocks the mistake. Reuse existing tooling in the repo before adding a dependency.
4. **Prove it bites.** Trigger the old mistake on purpose, show the rule fails/blocks it. Then show clean code passes. No proof = not done.
5. **Report.** Layer chosen, why not harder/softer, what the rule does NOT catch.

## Don't

- Do not add the rule to CLAUDE.md "to be safe" on top of a hard rule. Duplicate soft rule = noise.
- Do not ban things the user did not hit repeatedly. One-off mistakes do not need a rule.
- Do not claim the rule works without step 4.
