quiz
1.Kenapa membaca file 2000 baris penuh lewat Read lebih "mahal" daripada Grep dulu baru baca bagian spesifik?
karena tidak ada context dan constraints, jadi claude membaca seluruh 2000 baris itu meskipun tidak diperlukan sebenarnya

Kalau kamu ganti model di tengah sesi panjang, apa konsekuensinya ke biaya giliran berikutnya?
claude memiliki feature prompt caching , jika sering berpindah model di tengah sesi feature ini jadi hangus

Kapan sebaiknya kamu pakai subagent daripada mengerjakan riset langsung di context utama?
ketika berada di codebase besar, jadi task di delegasikan ke subagent , context utama hanya mendapatkan summarize nya

latihan
Jalankan /context — lihat apa saja yang memenuhi context window kamu saat ini.
claude-sonnet-5
80.4k / 967.0k tokens (8%)
Category
Tokens
Usage
System prompt
9.5k
1.0%
System tools
18.0k
1.9%
Custom agents
2.3k
0.2%
Memory files
1.7k
0.2%
Skills
5.4k
0.6%
Messages
43.6k
4.5%
Autocompact buffer
33.0k
3.4%
Free space
853.6k
88.3%
Memory files
/memory
d:\learn\CLAUDE.md
1.5k
~\.claude\projects\d--learn\memory\MEMORY.md
122
Custom agents
/agents
pr-review-toolkit:code-simplifier
736
pr-review-toolkit:silent-failure-hunter
523
pr-review-toolkit:code-reviewer
291
pr-review-toolkit:type-design-analyzer
188
pr-review-toolkit:pr-test-analyzer
179

Account & Usage
Account
Auth method
Claude AI
Email
mohamadsopian9@gmail.com
Organization
mohamadsopian9@gmail.com's Organization
Plan
Claude pro
Usage
Session (5hr)
0%
Weekly (7 day)
10%
Resets in 5d
Manage usage on claude.ai
What’s contributing to your limits usage?
Day
Week
Approximate, based on local sessions on this machine — does not include other devices or claude.ai
Last 24h · these are independent characteristics of your usage, not a breakdown
80% of your usage was at >150k context
Longer sessions are more expensive even when cached. /compact mid-task, /clear when switching to new tasks.
Skills
% of usage
/superpowers:systematic-debugging
7%
Plugins
% of usage
superpowers

Tulis ulang satu prompt "boros" yang biasa kamu pakai (misal "tolong benerin ini") jadi versi spesifik pakai template Goal/Context/Constraint/Format di atas.
versi boros : tolong cek error di uploadService.java
versi spesifik :
GOAL: validasi jika file upload diatas 5 mb, throw 400 bad request
CONTEXT: cek di /path/to/code/uploadService.java 
CONSTRAINTS: jangan mengubah flow existing , atau kalau ragu tulis kenapa dibuat seperti itu
FORMAT : message file yang anda upload terlalu besar

####

Menurutmu, pattern ini match ke command apa saja? Coba pikirkan bentuk paling longgar dan paling ketat yang mungkin ter-cover.
pattern ini match ke semua command git. Bentuk paling longgar nya adalah bisa melakukan commit dalam repository yang ada didalam sandbox dan 
bentuk paling ketat nya adalah selain di dalam repository bisa juga ke additionalDirectories.

Apakah pattern ini terlalu longgar atau terlalu sempit untuk workflow commit sehari-hari kamu? Kenapa?
ini sudah longgar, dan tidak masalah, karena perubahan masih ada di local, dan butuh approval user untuk push.

Kalau kamu jadi reviewer PR yang isinya nambah rule ini ke .claude/settings.json tim (bukan local), apakah kamu approve? Alasannya?
apabila di tim saya sudah mengimplementasikan police-as-code saya mungkin sangat hati hati karena ini memiliki impact yang besar. tapi kalau belum 
mengimplementasikan police-as-code saya mungkin akan approve.


PS D:\learn> cat d:\learn\.claude\settings.local.json
{
  "permissions": {
    "allow": [
      "Bash(ls \"C:\\\\Users\\\\eluon\\\\.claude\\\\projects\\\\d--learn\\\\memory\\\\\" 2>/dev/null; cat \"C:\\\\Users\\\\eluon\\\\.claude\\\\projects\\\\d--learn\\\\memory\\\\MEMORY.md\" 2>/dev/null)",
      "Read(//c/Users/eluon/.claude/projects/d--learn/memory\" 2>/dev/null; cat C:Userseluon.claudeprojectsd--learnmemoryMEMORY.md 2>/dev/**)",
      "Bash(ls -la 'C:\\\\Users\\\\eluon\\\\.claude\\\\projects\\\\d--learn\\\\memory\\\\' 2>&1; echo \"---\"; cat 'C:\\\\Users\\\\eluon\\\\.claude\\\\projects\\\\d--learn\\\\memory\\\\MEMORY.md' 2>&1)",
      "Read(//c/Users/eluon/.claude/projects/d--learn/memory/**)",
      "Bash(mkdir -p /d/learn/courses/code-review)",
      "WebSearch",
      "Bash(mkdir -p d:/learn/courses/llm && ls d:/learn/courses)",
      "WebFetch(domain:roadmap.sh)",
      "WebFetch(domain:refactoring.guru)",
      "Bash(mkdir -p \"d:\\\\learn\\\\courses\\\\design-patterns\" && ls \"d:\\\\learn\\\\courses\")",
      "Bash(mkdir -p \"d:/learn/courses/ml-fundamentals\")",
      "Bash(bash \"/c/Users/eluon/.claude/plugins/cache/claude-plugins-official/superpowers/6.2.0/skills/subagent-driven-development/scripts/sdd-workspace\" \"ai-los/PLAN_UNIVERSAL_LOS.md\")",
      "Bash(git -C /d/learn status --short)",
      "Bash(git -C /d/learn add .gitignore)",
      "Bash(git -C /d/learn commit -m 'chore: ignore .superpowers/ SDD workspace directory *)",
      "Bash(bash \"/c/Users/eluon/.claude/plugins/cache/claude-plugins-official/superpowers/6.2.0/skills/subagent-driven-development/scripts/task-brief\" \"ai-los/PLAN_UNIVERSAL_LOS.md\" 1)",
      "Bash(bash \"/c/Users/eluon/.claude/plugins/cache/claude-plugins-official/superpowers/6.2.0/skills/subagent-driven-development/scripts/task-brief\" \"ai-los/PLAN_UNIVERSAL_LOS.md\" 2)",
      "Bash(git -C /d/learn rev-parse HEAD)",
      "Bash(bash \"/c/Users/eluon/.claude/plugins/cache/claude-plugins-official/superpowers/6.2.0/skills/subagent-driven-development/scripts/task-brief\" \"ai-los/PLAN_UNIVERSAL_LOS.md\" 3)",
      "Bash(git add *)",
      "Bash(git commit -m ' *)",
      "Bash(bash \"/c/Users/eluon/.claude/plugins/cache/claude-plugins-official/superpowers/6.2.0/skills/subagent-driven-development/scripts/task-brief\" \"ai-los/PLAN_UNIVERSAL_LOS.md\" 5)"
    ],
    "additionalDirectories": [
      "D:\\learn\\courses",
      "d:\\learn\\courses"
    ]
  }
}

rule :
1.command git akan saya buatkan config di sandbox nya terkait command yang di allow, restricted, jadi bisa reuseable
2.command ini bash \"/c/Users/eluon/.claude/plugins/cache/claude-plugins-official/superpowers/6.2.0/skills/subagent-driven-development/scripts/task-brief\" berulang, harusnya di buat config di sandbox juga ketika
superpowers di allow
3.command mkdir akan saya buatkan juga di config sandbox hanya di allow di project repository active dan additionalDirectories


Assessment — tutup Lesson 3.1
Jawab singkat, lalu kita lanjut ke Lesson 3.2 (Hooks):

Kalau ada rule Bash(npm run *) di allow, tuliskan satu command "jahat" yang tetap lolos rule ini tapi bukan yang dimaksud pembuat rule.
npm run dangerous

Kenapa deny selalu menang lawan allow, walau allow-nya ada di level yang lebih spesifik (mis. project vs user)? Apa analoginya di sistem keamanan lain yang kamu tahu?
karena deny > allow. misal IAM policy atau Kubernetes RBAC.

Kapan menurutmu ask (bukan allow atau deny) adalah pilihan paling tepat untuk sebuah command?
ketika allow dan deny tidak sesuai dengan skenario yang kita inginkan, dan ada hal lain yang perlu ditinjau ulang.