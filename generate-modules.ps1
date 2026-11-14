#.\generate-modules.ps1 d:\learn\courses\communication-skills\roadmap.md D:\learn\progress\communication-skills
# Generate satu file per MODULE: "Module N - Judul.md", berisi tree: "# Modul" lalu tiap lesson (sub-module) "   # Judul"

param(
    [Parameter(Mandatory = $true)]
    [string]$SourceFile,

    [Parameter(Mandatory = $true)]
    [string]$TargetPath
)

Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

if (-not (Test-Path -LiteralPath $SourceFile -PathType Leaf)) {
    throw "Source file tidak ditemukan: $SourceFile"
}

if (-not (Test-Path -LiteralPath $TargetPath)) {
    New-Item -ItemType Directory -Path $TargetPath -Force | Out-Null
}

$content = Get-Content -LiteralPath $SourceFile -Raw -Encoding UTF8

# Match module headings: "## Module 5 - Judul" (dash: em, en, atau hyphen)
$pattern = '(?m)^##\s+Module\s+(\d+)\s*[\u2014\u2013-]\s*(.+?)\s*$'
$moduleMatches = [regex]::Matches($content, $pattern)

if ($moduleMatches.Count -eq 0) {
    Write-Warning "Tidak ditemukan heading '## Module N - Judul'."
    exit 0
}

$dash = [char]0x2014
$invalid = [regex]::Escape(-join [IO.Path]::GetInvalidFileNameChars())

$created = 0
$skipped = 0

for ($i = 0; $i -lt $moduleMatches.Count; $i++) {

    $match  = $moduleMatches[$i]
    $number = $match.Groups[1].Value
    $title  = $match.Groups[2].Value.Trim()

    # Blok teks modul ini: dari heading sampai heading Module berikutnya
    $start = $match.Index
    $end   = if ($i + 1 -lt $moduleMatches.Count) { $moduleMatches[$i + 1].Index } else { $content.Length }
    $block = $content.Substring($start, $end - $start)

    # Sub-module (lesson) = bullet checkbox "* [ ] teks"; judul pendek = teks sebelum ":" pertama
    $lessonBody = ""
    foreach ($lm in [regex]::Matches($block, '(?m)^\s*[*-]\s*\[[ xX]\]\s*(.+?)\s*$')) {
        $lessonTitle = ($lm.Groups[1].Value -split ':|\s[\u2014\u2013]\s', 2)[0].Trim()
        $lessonTitle = $lessonTitle -replace '^\d+\.\d+\s+', ''   # buang nomor "2.1 " kalau roadmap sudah menyertakan
        $lessonBody += "   # $lessonTitle`n`n"
    }

    # Judul bisa mengandung ":" dll yang ilegal di nama file Windows
    $safeTitle = ($title -replace "[$invalid]", '-').Trim()
    $fileName  = "Module $number $dash $safeTitle.md"
    $targetFile = Join-Path $TargetPath $fileName

    # Never overwrite existing file
    if (Test-Path -LiteralPath $targetFile -PathType Leaf) {
        Write-Host "[SKIP] $fileName sudah ada"
        $skipped++
        continue
    }

    Set-Content -LiteralPath $targetFile -Value "# $title`n`n$lessonBody" -Encoding UTF8

    Write-Host "[CREATE] $fileName"
    $created++
}

Write-Host ""
Write-Host "================================="
Write-Host "Course Module Generator"
Write-Host "================================="
Write-Host "Created : $created"
Write-Host "Skipped : $skipped"
Write-Host "Target  : $TargetPath"
Write-Host "================================="
