#.\generate-modules.ps1 d:\learn\courses\communication-skills\roadmap.md D:\learn\progress\communication-skills
# Generate satu file per MODULE (bukan per lesson): "Module N - Judul.md"

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

foreach ($match in $moduleMatches) {

    $number = $match.Groups[1].Value
    $title  = $match.Groups[2].Value.Trim()

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

    Set-Content -LiteralPath $targetFile -Value "# $title`n" -Encoding UTF8

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
