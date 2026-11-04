# Advanced SQL & Reporting Query Patterns
  # CTE: Non-Recursive untuk Query Terbaca, Recursive untuk Data Hierarkis
  query besar tanpa CTE itu mimpi buruk, susah dibaca, bersarang,dengan CTE query besar lebih mudah dibaca dan di maintain.

  CTE bisa dipakai CTE setelahnya, dan CTE yang sama bisa dipakai berulang ulang.

  bisa untuk non recursive dan recursive juga.

  namun CTE tidak otomatis jadi lebih cepat.

  aturan emas :
  reporting sebaiknya di kerjakan di query 80%, logic ada disana, gunakan CTE, jangan di java.

  jangan dilakukan :
  1. mengganggap CTE itu optimasi, CTE soal keterbacaan
  2. recursive tanpa pengaman siklik

  # Window Function: Running Total, Top-N per Grup, Perbandingan Periode
  



  