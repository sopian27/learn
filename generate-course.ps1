#.\generate-course.ps1 d:\learn\courses\film-criticism\roadmap.md D:\learn\playground\film-criticsm

param(
    [Parameter(Mandatory = $true)]
    [string]$SourceFile,

    [Parameter(Mandatory = $true)]
    [string]$TargetPath
)

Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

# Validate source
if (-not (Test-Path -LiteralPath $SourceFile -PathType Leaf)) {
    throw "Source file tidak ditemukan: $SourceFile"
}

# Create target directory if needed
if (-not (Test-Path -LiteralPath $TargetPath)) {
    New-Item -ItemType Directory -Path $TargetPath -Force | Out-Null
}

# Read roadmap
$content = Get-Content -LiteralPath $SourceFile -Raw -Encoding UTF8

# Match lesson lines:
# * [ ] 1.1 Lesson Title
# * [x] 5.2 Lesson Title
# * [ ] 10.1 Lesson Title
$pattern = '(?m)^\s*\*\s*\[[ xX]\]\s+(\d+\.\d+)\s+(.+?)\s*$'

$matches = [regex]::Matches($content, $pattern)

if ($matches.Count -eq 0) {
    Write-Warning "Tidak ditemukan lesson dengan format seperti 1.1, 1.2, 10.1, dst."
    exit 0
}

$created = 0
$skipped = 0

foreach ($match in $matches) {

    $lessonNumber = $match.Groups[1].Value
    $lessonTitle  = $match.Groups[2].Value.Trim()

    $fileName = "$lessonNumber.md"
    $targetFile = Join-Path $TargetPath $fileName

    # Never overwrite existing file
    if (Test-Path -LiteralPath $targetFile -PathType Leaf) {
        Write-Host "[SKIP] $fileName sudah ada"
        $skipped++
        continue
    }

    $fileContent = @"
# $lessonNumber — $lessonTitle

## Objective

## Materi

## Exercise

## Notes

## Applied Practice

## Interview Questions

## Review
"@

    Set-Content `
        -LiteralPath $targetFile `
        -Value $fileContent `
        -Encoding UTF8

    Write-Host "[CREATE] $fileName"
    $created++
}

Write-Host ""
Write-Host "================================="
Write-Host "Course Lesson Generator"
Write-Host "================================="
Write-Host "Created : $created"
Write-Host "Skipped : $skipped"
Write-Host "Target  : $TargetPath"
Write-Host "================================="