# AI-LOS Listening System — Design Spec

Date: 2026-09-05
Status: Approved (design), pending implementation plan

## Problem

Course material in the Obsidian vault (`Courses/<Course>/Module N - <Judul>.md`) is text-only. The user wants to listen to lessons (commute, chores, etc.) instead of always reading. Requirement: fully free, natural-sounding Indonesian voice, fully local tooling (Windows/PowerShell/Python), Obsidian stays source of truth, audio is a derived output only.

## Decisions locked before this doc (via clarifying questions)

1. **Scope v1**: only `Courses/` — the vault's `info tech`/daily-log folders are short journal notes, not structured lessons, out of scope.
2. **Granularity**: one merged MP3 per module file, chaptered internally via spoken section transitions (not one MP3 per lesson) — because one module file already bundles multiple lessons + a Mini Project, and the source `OBSIDIAN_SYNC.md` convention treats the module file as the atomic unit.
3. **Output location**: `Audio/<Course>/Module N - <Judul>.mp3` inside the same OneDrive vault, mirroring `Courses/<Course>/Module N - <Judul>.md` — reuses existing OneDrive sync to get audio onto the phone, no custom mobile sync needed.
4. **TTS engine**: `edge-tts` (unofficial wrapper around Microsoft Edge Read-Aloud neural voices), voices `id-ID-ArdiNeural` / `id-ID-GadisNeural`. Chosen over fully-offline `Piper` (`id_ID-news_tts-medium`) and `facebook/mms-tts-ind` because naturalness was stated as the single most important requirement, and both offline options sound noticeably more synthetic. `Kokoro-82M` was verified via research to not support Indonesian at all (en/es/fr/hi/it/ja/pt-br/zh only) and is excluded.
   - **Trade-off accepted**: edge-tts sends lesson text to Microsoft's servers over the network, is an unofficial/undocumented endpoint that could change or be blocked without notice, and requires internet at generation time. Given personal-use, non-sensitive course material and the naturalness requirement, this was accepted explicitly by the user.
5. **Code block narration**: rule-based deterministic templating (no LLM). A code fence is not read verbatim; it's summarized generically ("Ada blok kode di bagian ini, lihat langsung di catatan tertulis untuk detailnya" plus, where cheaply extractable via regex, the declared class/method/variable names). This is a deliberate v1 scope trade-off — accepted in exchange for zero cost and full determinism, over a local-LLM (Ollama) or Claude-API rewrite that would produce deeper semantic narration (e.g. actually explaining *why* a `ConcurrentHashMap` example matters) but adds cost, latency, and a moving dependency.

## Real heading vocabulary (verified against `Module 1 - Java Lanjut.md`)

Each lesson inside a module file consistently uses:
`### Objective / Prerequisite / Theory / Visualization / Simple Example / Real-world Example / Coding / Exercise / Task / Review (Senior Engineer) / Common Mistakes / Interview Questions / Summary`

Module file also ends with a `## Mini Project` block (its own Objective/Context/Kode yang harus diperbaiki/Task/Review sub-structure). This vocabulary drives the narration transition map below — it is not invented, it is read from real course content so transitions actually match the material.

## Architecture

```
Obsidian Vault/Courses/<Course>/Module N - <Judul>.md   (source, read-only)
              |
              v
   [1] Scanner        -- walk Courses/, mirror path to Audio/, hash source to detect stale
              |
              v
   [2] Markdown Parser -- strip YAML frontmatter, tokenize via markdown-it-py
              |
              v
   [3] Narration Builder -- token -> natural spoken text per token type (heading/list/table/
                            code/inline-code/link/image), using heading-aware transition map
              |
              v
   [4] Chunker        -- split per section, sentence-safe, max N chars per chunk
              |
              v
   [5] TTS Engine (edge-tts) -- synthesize each chunk to mp3, retry on network error
              |
              v
   [6] Audio Merger   -- concatenate chunk mp3s into one final module mp3 (pydub + ffmpeg,
                          short silence gap between sections)
              |
              v
   Obsidian Vault/Audio/<Course>/Module N - <Judul>.mp3
```

## Components

- **`scanner.py`** — walks `Courses/**/Module N - *.md` (and `Enterprise Project.md` capstones), computes the mirrored output path under `Audio/`, and decides per-file whether generation is needed: no output yet, or source MD5 hash changed since last successful generation (hash stored in cache), or `--force` was passed.

- **`markdown_parser.py`** — strips YAML frontmatter (`---` delimited block at file start), then tokenizes the remaining Markdown with `markdown-it-py`. Chosen over regex because it reliably distinguishes headings, ordered/unordered lists, tables, fenced code blocks, inline code spans, links, and images in one pass without the edge cases regex-based parsing accumulates (e.g. a `#` inside a code fence).

- **`narration_builder.py`** — walks parsed tokens and emits spoken-friendly text:
  - **Headings**: looked up in a transition-phrase table keyed by heading text (case-insensitive), e.g. `Theory` -> "Mari masuk ke teori.", `Common Mistakes` -> "Beberapa kesalahan umum yang perlu diwaspadai." An H1 (module title) gets an opening line ("Selamat datang di Module {N}: {title}."), an H2 (lesson title) gets "Lesson berikutnya: {title}.". Any heading text not in the table falls back to "Bagian berikutnya: {heading text}." — so an unfamiliar heading in some future module never crashes the pipeline, it just narrates generically.
  - **Visualization section (special case)**: this section's body is often a diagram/mermaid/ASCII-art block with little prose. Fenced code/mermaid content in this section is skipped entirely (same as the general code-block rule below); if the section has any surrounding prose paragraphs, they are read; the section always closes with "Diagram lengkap untuk konsep ini ada di catatan tertulis di Obsidian."
  - **Lists**: rendered as "Pertama, ...; kedua, ...; ketiga, ..." (ordinal words up to 10, then "lalu ..." after that) so it doesn't sound like a robotic bullet dump.
  - **Tables**: read row by row — "Baris pertama: kolom X, kolom Y adalah nilai .... Baris kedua: ...".
  - **Fenced code blocks**: never read verbatim. A cheap regex pass extracts declared class/method/variable names if present (e.g. `class\s+(\w+)`, `\w+\s+(\w+)\s*\(`) and produces "Ada blok kode di sini yang mendefinisikan {nama}, lihat detail lengkapnya di catatan tertulis." If nothing extractable, falls back to "Ada blok kode di bagian ini, lihat langsung di catatan tertulis untuk detailnya."
  - **Inline code** (`` `like this` ``): backticks stripped, term read as plain text (no phonetic rewriting in v1).
  - **Links**: link text is read, URL discarded (`[JEP 421](...)` -> "JEP 421").
  - **Images**: skipped; if meaningful alt text exists, "Ada gambar terkait {alt text} di catatan." is read, otherwise silently skipped.

- **`chunker.py`** — takes the narration text per top-level section (each `###`/`##` block) and splits at sentence boundaries (`.`, `!`, `?` followed by whitespace) so no chunk exceeds `max_chunk_chars` (default 1800) and no sentence is cut mid-way. Chunk boundaries double as the resume granularity.

- **`tts_engine.py`** — thin wrapper over `edge_tts.Communicate(text, voice, rate, pitch)`, one call per chunk, writes chunk mp3 to a temp working dir under `.cache/`. Wraps each call in retry logic: up to `retry_count` attempts (default 3) with exponential backoff (`retry_backoff_seconds * 2^attempt`), catching network/connection errors specifically.

- **`audio_merge.py`** — loads all chunk mp3s for a module in order via `pydub.AudioSegment`, inserts a short silence (e.g. 400ms) between section boundaries, exports one final mp3 to the `Audio/` path. Requires system `ffmpeg` on PATH (pydub shells out to it); its absence is detected up front and reported as a fatal, actionable error before any TTS calls are made (fail fast, don't burn API calls then discover merge is impossible).

- **`cache.py`** — per-module JSON state file at `.cache/<course-slug>/<module-slug>.json` containing: source file MD5 hash, list of section chunks with their own text hash and generated chunk mp3 path/status, and final output path/status. On each run: if source hash matches cache and final output file exists and not `--force`, skip entirely. If source hash differs, or a section's chunk is missing/failed, only that section is regenerated — completed, unchanged chunks are reused. This is what makes `--all` idempotent and interruption-safe.

- **`ai_los_tts.py`** — CLI entry point, argument parsing, orchestrates scanner -> parser -> narration_builder -> chunker -> tts_engine -> audio_merge -> cache per module, `rich`-based progress display (current file, section, chunk N/M), writes failures to `logs/errors.log` and continues to the next module rather than aborting a whole `--all` batch run.

## Data flow / resume semantics

For a given module file:
1. Compute source MD5. If cache has same hash AND final mp3 exists AND not `--force` -> skip, log "up to date".
2. Otherwise: parse -> build narration -> chunk. For each chunk, check cache: if this exact chunk text hash already has a successful mp3 file on disk, reuse it (no re-synthesis, no network call). Otherwise synthesize (with retry) and record success in cache immediately after each chunk — so a mid-run interruption (Ctrl+C, crash, network drop) loses at most one in-flight chunk, not the whole module.
3. Once every chunk for the module has a successful mp3, merge in order into the final output mp3, write final hash+path to cache, delete temp chunk files.
4. If any chunk exhausts its retries, the module is marked failed for this run, logged to `logs/errors.log` with the specific chunk and error, and the batch (`--all`) moves to the next module rather than stopping.

## Error handling

- **ffmpeg missing**: detected once at startup (`shutil.which("ffmpeg")`), fatal with an actionable message (link/instruction to install and add to PATH) — checked before any TTS network calls to avoid wasted work.
- **edge-tts network/endpoint failure**: retried per chunk (3x, exponential backoff); if still failing, that module is skipped (not the whole run), reason logged.
- **Malformed/unexpected Markdown** (e.g. heading not in vocabulary table): never fatal — falls back to a generic transition phrase, pipeline keeps going.
- **Source files are never written to.** Only `Audio/`, `.cache/`, and `logs/` are touched by this tool.

## CLI

```powershell
python ai_los_tts.py "path/to/Module N - Judul.md"   # single file
python ai_los_tts.py --all                            # batch, skip up-to-date modules
python ai_los_tts.py --all --force                    # batch, regenerate everything
python ai_los_tts.py --voice-list                     # list id-ID edge-tts voices
python ai_los_tts.py --test                            # short sample per id-ID voice, for comparison
```

## Configuration (`config.yaml`)

```yaml
engine: edge-tts
voice: id-ID-GadisNeural
rate: "+0%"
pitch: "+0Hz"
source_dir: "C:/Users/eluon/OneDrive/Documents/Obsidian Vault/Courses"
output_dir: "C:/Users/eluon/OneDrive/Documents/Obsidian Vault/Audio"
cache_dir: ".cache"
log_dir: "logs"
max_chunk_chars: 1800
retry_count: 3
retry_backoff_seconds: 2
```

No values are hardcoded in source; swapping voice or paths only touches this file.

## Project layout

```
d:\learn\scripts\ai-los-tts\
  ai_los_tts.py
  config.yaml
  requirements.txt        # edge-tts, markdown-it-py, pyyaml, rich, pydub
  scanner.py
  markdown_parser.py
  narration_builder.py
  chunker.py
  tts_engine.py
  audio_merge.py
  cache.py
  .cache/                 # gitignored, per-module state + temp chunk mp3s
  logs/                   # gitignored, errors.log
```

## Testing / verification approach

No formal unit test suite in v1 (per "don't over-engineer" — this is a personal CLI utility, not a service). Verification is manual and end-to-end:
1. `--test` first, listen to both `id-ID-ArdiNeural` and `id-ID-GadisNeural` samples, confirm pronunciation quality and pick default voice in config.
2. Run against one real module (`Module 1 - Java Lanjut.md`), listen to the full output: confirm section transitions sound natural (mentor-like, not screen-reader), confirm code blocks are summarized not read verbatim, confirm no mid-sentence cuts at chunk boundaries.
3. Interrupt a run mid-module (Ctrl+C) and re-run: confirm it resumes from the last completed chunk instead of restarting from scratch.
4. Run `--all` twice in a row: confirm the second run reports everything up-to-date and makes no network calls.
5. Edit one module's source text slightly and re-run `--all`: confirm only that module regenerates.

## Explicitly out of scope for v1

Spotify/podcast integration, playlists, chapter metadata embedded in mp3 (ID3 chapters), automatic summaries, background music, playback speed control, resume-listening-position tracking, custom mobile sync (OneDrive already handles this), any server/database/Docker/Kubernetes/web UI.

## Future improvement ideas (not built now)

- ID3 chapter markers per lesson inside the merged mp3 (many podcast apps support tap-to-jump chapters) instead of relying purely on spoken transitions.
- Smarter code-block narration via a local LLM (Ollama) as an optional, config-gated upgrade over the rule-based summarizer, for users who want deeper explanation without cloud cost.
- Camel-case/identifier pronunciation splitting (e.g. `ConcurrentHashMap` -> "Concurrent Hash Map") if raw pronunciation from edge-tts proves awkward in practice.
- Fallback to Piper (offline) automatically if edge-tts is unreachable for an extended period, so the tool degrades gracefully instead of being fully blocked by a Microsoft-side outage.
