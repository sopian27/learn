# Course Roadmap

## Course Information

* Course Name: React — dari 0 sampai Expert (Fullstack Track dengan TypeScript penuh)
* Category: Frontend Engineering / Fullstack Engineering (pelengkap dari backend Java/Spring Boot)
* Difficulty: Level 0 (Belum pernah TypeScript, JavaScript sudah dikuasai) → Level 4 (Expert Frontend/Fullstack Engineer)
* Estimated Duration: 14 modul, 3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: JavaScript (ES6+) sudah dikuasai, HTML/CSS sudah nyaman (flexbox/grid, responsive), familiar dengan Java/Spring Boot backend (dipakai untuk modul integrasi). TypeScript diajarkan dari nol, mendalam sampai advanced.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban Anda:

1. **Kedalaman TypeScript**: Anda memilih **TypeScript full sampai expert**. Karena itu, TypeScript mendapat 3 modul khusus (Module 2-4: Fundamentals → Intermediate → Advanced) sebelum masuk React sama sekali, lalu **setiap** lesson React sesudahnya ditulis dalam `.tsx` dan mengasumsikan Anda sudah paham typing. Ini beda dari pola "secukupnya" — Anda akan benar-benar menguasai generics, utility types, dan advanced types, bukan cuma `interface Props`.
2. **Cakupan Framework**: Anda memilih **React murni (Vite/SPA) dulu**, Next.js baru masuk di Module 13 sebagai kelanjutan. Ini supaya fondasi React (rendering model, hooks, state) benar-benar solid sebelum kena abstraksi SSR/RSC dari framework.
3. **Tujuan**: Anda memilih **upgrade skill sebagai backend engineer (Java/Spring Boot) agar bisa fullstack**. Karena itu, Module 10 (API Calls & Server State) dan Module 13 (Next.js & Deployment) punya sub-bagian **"Production Discussion: Spring Boot Integration"** yang menyambungkan langsung ke `standards/` repo ini (REST contract, JWT auth, CORS, reverse proxy) — bukan sekadar teori frontend berdiri sendiri.
4. **HTML/CSS**: Anda sudah nyaman, jadi **tidak ada modul penyegaran HTML/CSS** — langsung ke tooling & TypeScript.

Referensi riset yang dipakai menyusun roadmap ini: [roadmap.sh/react](https://roadmap.sh/react), [roadmap.sh/typescript](https://roadmap.sh/typescript) (struktur resmi topik & subtopik diambil dari PDF roadmap keduanya), dokumentasi resmi [react.dev](https://react.dev) (struktur "Learn React" & Hooks reference), dan [TypeScript Handbook](https://www.typescriptlang.org/docs/handbook/intro.html) resmi Microsoft.

---

# Learning Objectives

* [ ] Mahir TypeScript dari primitive types sampai generics, utility types, mapped types, conditional types — cukup dalam untuk membaca & menulis type definition library tingkat production
* [ ] Memahami mental model React (component sebagai fungsi UI, rendering & reconciliation) bukan cuma menghafal syntax
* [ ] Mahir seluruh hooks inti (useState, useEffect, useRef, useMemo, useCallback, useReducer, useContext) dan mampu membuat custom hooks yang reusable & type-safe
* [ ] Mampu membangun state management, routing, styling, dan form handling production-grade dengan tooling modern (Zustand, React Router, Tailwind, React Hook Form + Zod)
* [ ] Mampu mengintegrasikan React dengan backend REST API (khususnya Spring Boot) secara aman: auth token, error handling, caching server state dengan TanStack Query
* [ ] Mampu menulis test (unit, integration, e2e) untuk komponen dan hooks React
* [ ] Memahami performance optimization, error boundaries, suspense, dan pola arsitektur React tingkat lanjut
* [ ] Mampu membangun aplikasi production dengan Next.js dan men-deploy-nya
* [ ] Mampu berpikir seperti Staff Frontend Engineer: arsitektur skala besar, code review, accessibility, dan siap interview teknis React/TypeScript
* [ ] Menyelesaikan proyek portofolio fullstack: React + TypeScript terhubung ke backend Spring Boot, tested, dan ter-deploy

---

# Course Modules

## Module 1 — Environment Setup & Modern JavaScript Refresher untuk React

Description: Menyiapkan tooling dan menyegarkan bagian JavaScript yang paling sering dipakai React, tanpa mengulang dasar-dasar yang sudah Anda kuasai. Setiap lesson punya quiz cepat — kalau sudah paham, langsung skip.

Lessons:

* [ ] Setup environment: Node.js, package manager (pnpm/npm), VS Code extensions untuk React+TS
* [ ] Scaffolding project dengan Vite (kenapa Vite, bukan Create React App)
* [ ] ES6+ refresher yang krusial untuk React: destructuring, spread/rest, arrow function & `this`-binding, modules import/export, array methods (map/filter/reduce), optional chaining & nullish coalescing
* [ ] Git workflow dasar untuk course ini (branch per modul, commit per lesson)

Mini Project: Scaffold project Vite + React + TypeScript kosong dengan struktur folder awal yang rapi.

Status: Not Started

---

## Module 2 — TypeScript Fundamentals (dari 0)

Description: Bagian pertama dari 3 modul TypeScript. Membangun fondasi type system dari nol — ini yang akan dipakai di setiap baris kode React setelah ini.

Lessons:

* [ ] TypeScript vs JavaScript, TS-JS interoperability, kenapa industri memakainya
* [ ] Instalasi & konfigurasi: `tsconfig.json`, compiler options penting, menjalankan TS (`tsc`, `ts-node`, TS Playground)
* [ ] Primitive types (boolean, number, string, void, undefined, null) & object types (interface, class dasar, enum, array, tuple)
* [ ] Top types (`any`, `unknown`, `object`) & bottom type (`never`) — kapan masing-masing dipakai
* [ ] Type inference, type compatibility, dan assertions (`as`, `as const`, non-null assertion, `satisfies`)

Mini Project: Konversi sebuah script JavaScript kecil (util functions) menjadi TypeScript strict mode, tanpa satupun `any`.

Status: Not Started

---

## Module 3 — TypeScript Intermediate: Functions, Interfaces, Classes, Generics

Description: Bagian kedua TypeScript — cara menulis type yang menggabungkan informasi, mempersempit tipe (narrowing), dan generics sebagai fondasi menulis komponen/hook React yang reusable.

Lessons:

* [ ] Combining types: union, intersection, type alias, `keyof` operator
* [ ] Type guards & narrowing (`typeof`, `instanceof`, truthiness, type predicates)
* [ ] Typing functions & function overloading
* [ ] Types vs Interfaces, extending interfaces, hybrid types
* [ ] Classes di TypeScript (constructor params, access modifiers, abstract class, inheritance vs polymorphism, method overriding)
* [ ] Generics: generic types & generic constraints

Mini Project: Bangun generic `Repository<T>` kecil (in-memory CRUD) yang type-safe sepenuhnya menggunakan generics.

Status: Not Started

---

## Module 4 — TypeScript Advanced: Utility Types & Advanced Types

Description: Bagian ketiga TypeScript — level yang membedakan "bisa pakai TS" dengan "expert TS". Ini yang dipakai di type definition library-library React populer.

Lessons:

* [ ] Utility types: `Partial`, `Pick`, `Omit`, `Readonly`, `Record`, `Exclude`, `Extract`, `NonNullable`, `Parameters`, `ReturnType`, `InstanceType`, `Awaited`
* [ ] Advanced types: mapped types, conditional types, literal types, template literal types, recursive types
* [ ] Decorators (overview), TypeScript modules (namespace vs ambient/external modules) sekilas
* [ ] Ecosystem: ESLint + Prettier untuk TypeScript, konvensi linting production

Mini Project: Bangun type-safe API response mapper (mengubah shape response backend jadi type frontend) memakai mapped types & utility types — akan dipakai lagi di Module 10.

Status: Not Started

---

## Module 5 — React Fundamentals: Components & JSX

Description: Mulai React sungguhan — mental model "component sebagai fungsi UI", ditulis dalam TSX sejak baris pertama.

Lessons:

* [ ] Kenapa React & mental model component-based UI
* [ ] JSX/TSX syntax & bagaimana JSX di-compile
* [ ] Functional components & typing props dengan `interface`
* [ ] Props vs state, conditional rendering, composition (children/slot pattern)
* [ ] Lists & keys, typing event handlers, refs dasar

Mini Project: Component library kecil full typed (Button, Card, Badge) dengan halaman showcase.

Status: Not Started

---

## Module 6 — Hooks Mendalam

Description: Hooks adalah jantung React modern. Modul ini menuntaskan hooks inti sampai bisa membuat custom hooks sendiri secara type-safe.

Lessons:

* [ ] Rendering model & component lifecycle React (mental model, bukan class lifecycle lama)
* [ ] `useState` dengan typing yang benar
* [ ] `useEffect`: dependency array, cleanup function, kesalahan umum
* [ ] `useRef`, `useMemo` & `useCallback` (kapan perlu, kapan premature optimization)
* [ ] `useReducer` dengan typed reducer pattern, `useContext`
* [ ] Membuat custom hooks generic & reusable, Rules of Hooks

Mini Project: Custom hook generic (`useFetch<T>` atau `useLocalStorage<T>`) dipakai di komponen nyata.

Status: Not Started

---

## Module 7 — State Management & Routing

Description: Kapan state lokal/Context cukup, kapan butuh state management eksternal, dan cara routing production-grade.

Lessons:

* [ ] Kapan cukup lifting state/Context API, kapan butuh state management eksternal
* [ ] Zustand: typed store, pattern production
* [ ] Alternatif & trade-off sekilas: Redux Toolkit, Jotai, MobX
* [ ] React Router: routing, nested routes, loaders, protected routes dengan TypeScript

Mini Project: Aplikasi multi-halaman dengan routing + global state (misal auth/cart) memakai Zustand.

Status: Not Started

---

## Module 8 — Styling & Component Architecture

Description: Cara styling React yang scalable, dan bagaimana merancang design system reusable.

Lessons:

* [ ] Tailwind CSS setup & workflow di React
* [ ] CSS Modules sebagai alternatif
* [ ] Headless component libraries (Radix UI) vs styled libraries (shadcn/ui, MUI, Chakra) — trade-off
* [ ] Merancang design system/reusable component architecture yang scalable

Mini Project: Re-style component library dari Module 5 menjadi design system kecil yang konsisten dengan Tailwind + Radix/shadcn.

Status: Not Started

---

## Module 9 — Forms & Validation

Description: Form adalah salah satu sumber bug terbanyak di frontend. Modul ini menuntaskan pola form production dengan validasi type-safe.

Lessons:

* [ ] Controlled vs uncontrolled forms
* [ ] React Hook Form: typed form state, performa (uncontrolled by default)
* [ ] Zod: schema validation & type inference dari schema (`z.infer`)
* [ ] Integrasi React Hook Form + Zod end-to-end
* [ ] UX form production: error state, submit state, optimistic UI dasar

Mini Project: Form kompleks (nested fields, validasi async, misal form registrasi/checkout) full typed dari schema Zod.

Status: Not Started

---

## Module 10 — API Calls & Server State

Description: Menghubungkan React ke backend nyata — modul paling relevan dengan tujuan Anda upgrade ke fullstack.

Lessons:

* [ ] REST API calls dengan `fetch` & Axios (typed response)
* [ ] TanStack Query (react-query): caching, invalidation, mutation, optimistic update
* [ ] Pola error handling & loading state yang konsisten
* [ ] Autentikasi dari sisi frontend: menyimpan & mengirim JWT/session token dengan aman

**Production Discussion: Spring Boot Integration** — kontrak REST API antara React dan Spring Boot, CORS configuration, generate TypeScript types dari OpenAPI spec (`standards/API_STANDARDS.md` bila ada), dan pola auth header yang konsisten dengan Spring Security.

Mini Project: Hubungkan frontend ke REST API Spring Boot (API sederhana yang Anda buat sendiri atau dari latihan Java sebelumnya) — CRUD penuh dengan TanStack Query + auth token.

Status: Not Started

---

## Module 11 — Testing

Description: Testing pyramid untuk frontend — dari unit test komponen sampai e2e flow.

Lessons:

* [ ] Testing pyramid untuk frontend (unit, integration, e2e)
* [ ] Vitest setup & konfigurasi
* [ ] React Testing Library — testing behavior, bukan implementation detail
* [ ] Mocking API calls (MSW), testing custom hooks
* [ ] E2E testing overview dengan Playwright

Mini Project: Test suite (unit + integration) untuk component library & form dari modul sebelumnya, plus 1 e2e flow dengan Playwright.

Status: Not Started

---

## Module 12 — Advanced React & Performance

Description: Yang membedakan developer React menengah dengan yang senior — memahami apa yang terjadi "di balik layar".

Lessons:

* [ ] Rendering & reconciliation React secara mendalam
* [ ] Performance profiling dengan React DevTools Profiler
* [ ] Code splitting & lazy loading
* [ ] Error Boundaries, Suspense (data fetching pattern), Portals
* [ ] Animasi dengan Framer Motion
* [ ] Sekilas Server APIs/React Server Components sebagai konsep (persiapan Next.js)

Mini Project: Optimasi performa aplikasi dari modul-modul sebelumnya (before/after profiling), tambahkan error boundary & suspense loading state.

Status: Not Started

---

## Module 13 — Production Framework: Next.js & Deployment

Description: Migrasi dari pemahaman React murni ke framework production yang dipakai kebanyakan perusahaan.

Lessons:

* [ ] Kenapa butuh framework: SSR/SSG/ISR vs pure SPA
* [ ] Next.js App Router: routing & layouts dasar
* [ ] Data fetching di server component
* [ ] Migrasi sebagian aplikasi dari Vite SPA ke Next.js
* [ ] Build & deployment dasar (Vercel atau Docker)

**Production Discussion: Spring Boot Integration** — pola reverse proxy (Nginx/API gateway) di depan backend Spring Boot, environment config per-environment, dan CORS di production — konsisten dengan `standards/` repo ini.

Mini Project: Deploy 1 aplikasi Next.js yang mengonsumsi API Spring Boot, live di internet (Vercel gratis atau Docker lokal).

Status: Not Started

---

## Module 14 — Menjadi Frontend/Fullstack Engineer yang Expert

Description: Level di mana Anda bisa memimpin keputusan arsitektur frontend, bukan cuma implementasi — persiapan untuk Enterprise Project.

Lessons:

* [ ] Arsitektur frontend skala besar: feature-based folder structure, monorepo overview (Turborepo/Nx sekilas)
* [ ] Design pattern React tingkat lanjut: compound components, headless/controlled pattern
* [ ] Code review checklist untuk React & TypeScript
* [ ] Studi kasus: bagaimana Netflix, Airbnb, Stripe, GitHub membangun frontend mereka
* [ ] Accessibility (a11y) production-level
* [ ] Interview prep: coding challenge React & system design frontend

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Full Fullstack Engineer Simulation** — bangun 1 aplikasi production yang menggabungkan seluruh course: (1) Frontend React + TypeScript (mulai dari Vite SPA, sebagian dimigrasikan ke Next.js), (2) state management dengan Zustand, (3) server state dengan TanStack Query terhubung ke REST API Spring Boot (JWT auth, PostgreSQL — sesuai stack di root `CLAUDE.md`), (4) form kompleks dengan React Hook Form + Zod, (5) styling dengan Tailwind + component library (shadcn/Radix), (6) test suite (Vitest + React Testing Library + minimal 1 flow Playwright), (7) deployment (Vercel/Docker) dengan CORS & reverse proxy production-ready.

Acceptance Criteria:

* Seluruh kode TypeScript strict mode, tanpa `any` yang tidak terjustifikasi
* Frontend berhasil melakukan auth (login/register) dan CRUD penuh ke backend Spring Boot
* State server (data dari API) dan state client (UI state) dipisah dengan jelas (TanStack Query vs Zustand)
* Form tervalidasi end-to-end dengan Zod, error state ditampilkan dengan benar
* Test suite berjalan hijau (unit + integration + minimal 1 e2e)
* Aplikasi ter-deploy dan bisa diakses (Vercel/Docker), CORS dikonfigurasi benar untuk production
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/14

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — Setup environment (Node.js, package manager, VS Code extensions)
