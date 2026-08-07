# n8n Automation — Domain Pack

## Domain Name

n8n Automation (Workflow Automation & AI Agents)

## Scope

Designing, building, securing, and maintaining automated workflows and AI-agent-powered personal assistants using n8n (self-hosted or cloud): triggers, core/app nodes, API and webhook integration, credentials and authentication, data transformation and expressions, error handling and reliability patterns, and AI Agent/LangChain nodes (chat models, tools, memory, RAG). The end goal this domain is scoped around: the learner can design and run their own "Jarvis-lite" personal AI assistant automation system.

Does NOT cover: general-purpose backend software engineering or writing production services from scratch (that is the `software-engineering` domain — n8n's Code node is used only as a supporting tool here, not taught as a general programming course); other no-code/low-code platforms (Zapier, Make.com) except as brief comparison points in Practitioner Reference Frame; building/training LLMs themselves (covered, if at all, by a future `courses/llm` style track — this domain only *orchestrates* existing models via n8n's AI nodes).

## Trusted Sources / Research Priority

1. **docs.n8n.io** — official documentation. Ground truth for node behavior, parameters, and expressions syntax; always the final tie-breaker on conflict.
2. **docs.n8n.io/courses** (official Level 1 & Level 2 text courses) and **learn.n8n.io** (n8n Academy video courses) — official structured curricula; mirror their sequencing where it doesn't conflict with this repo's Universal Learning Loop.
3. **community.n8n.io** (forum, especially the "Docs & Tutorials" and "Tutorials" categories) — community-vetted patterns and troubleshooting; treat as corroborating evidence, not sole authority, since forum answers can go stale as nodes change.
4. **n8n.io/workflows** — official template library. Used as real-world worked examples, always cross-checked against current docs before teaching a pattern from a template (templates can lag behind node updates).
5. **github.com/n8n-io/n8n** — source of truth when docs lag behind a fast-moving area (this matters most for the AI/LangChain nodes, which change quickly); check release notes/changelog before teaching bleeding-edge AI node behavior.

Never build a lesson from a third-party blog or YouTube tutorial alone (this space is heavily SEO/affiliate-driven and drifts out of date fast) — always cross-check against source 1 before treating a claim as authoritative, per `standards/UNIVERSAL_STANDARDS.md`'s Vetting a Source bar.

## Applied Practice Definition

Building or extending a real, runnable n8n workflow every lesson — exported as workflow JSON, not a screenshot or description. Where a lesson needs a paid/external API and the learner doesn't have credentials yet, a mock/public API or n8n's built-in test webhook is used instead, clearly labeled as a stand-in, and swapped for the real integration once available. Every workflow must actually execute (checked via n8n's execution log/pinned data), not just look correct on the canvas.

## Project Tiers

- **Mini Project** (per module): a single-purpose workflow demonstrating that module's skill only — e.g. a webhook that validates input and routes it, or an error-handled API call with a Telegram notification on failure.
- **Intermediate Project** (per major section): a multi-node workflow combining several modules' skills — e.g. a simple ETL pipeline, or a chat-based AI agent with tools and memory.
- **Capstone / Portfolio Project** (end of course): a working personal AI assistant automation system ("Jarvis-lite") — a chat-reachable AI Agent with multiple real tools, memory, error handling, and at least one proactive scheduled automation — documented and saved to `portofolio/` as exported workflow JSON plus a README explaining the architecture.

## Review Style

On top of the Universal Review Rubric (Strengths / Weaknesses / Actionable Improvements / Score 0–100), every workflow review additionally checks:

- **Reliability** — does the workflow handle failure paths (API errors, empty data, timeouts), or does it only work on the happy path?
- **Security** — are credentials stored via n8n's credential system (never hardcoded in a node/expression), are webhooks authenticated, and does each credential use least-privilege scope?
- **Efficiency** — does the workflow avoid unnecessary API calls, redundant nodes, or wasteful loops that would matter at real execution volume/cost (including LLM token cost for AI nodes)?
- **Maintainability** — are nodes named descriptively, is intent documented with sticky notes, and is repeated logic factored into sub-workflows rather than copy-pasted?

## Assessment Form

A short concept-check quiz after each lesson's theory portion (node behavior, expression syntax, trigger types), plus periodically a "trace the execution" exercise — given a workflow JSON or screenshot, predict the data shape at a specific node before running it — and a self-evaluation pass against the four Review Style criteria above before submitting Applied Practice for review.

## Practitioner Reference Frame

Compare how n8n's own official template library approaches a problem against how Zapier or Make.com would solve the same automation (more constrained, more polished no-code UX, usually more expensive at scale) against how a developer would solve it with raw code/serverless functions (fully flexible, no vendor lock-in, but no visual debugging and higher build/maintenance cost). For AI-agent-specific questions, additionally reference n8n's own AI Agent documentation patterns and well-known community template authors on n8n.io/workflows as the "expert automation builder" reference point, versus a beginner's naive single-prompt approach.

## Domain-Specific Standards

None yet — `standards/UNIVERSAL_STANDARDS.md` is sufficient today. The Review Style criteria above (Reliability, Security, Efficiency, Maintainability) serve as this domain's house rules for now; promote them to a dedicated `domains/n8n-automation/standards/` file only if they need to grow more detailed than DOMAIN.md can hold.

## Domain Goal

Enable the learner to independently design, build, secure, and maintain n8n automations — from a single trigger-action workflow up through a multi-tool, memory-holding AI Agent — culminating in a personal AI assistant automation system the learner built, understands end-to-end, and can keep extending on their own after the course ends.
