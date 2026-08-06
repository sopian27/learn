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