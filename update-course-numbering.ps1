#.\update-course-numbering.ps1 -SourcePath "D:\learn\courses\blockchain\roadmap.md"

param(
    [Parameter(Mandatory = $true)]
    [string]$SourcePath
)

Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

# ============================================================
# Validate source
# ============================================================

if (-not (Test-Path -LiteralPath $SourcePath -PathType Leaf)) {
    throw "Source file tidak ditemukan: $SourcePath"
}

# ============================================================
# Output
# ============================================================

$sourceDirectory = Split-Path -Parent $SourcePath
$outputPath = Join-Path $sourceDirectory "roadmap.md"

# ============================================================
# Skip if output already exists
# ============================================================
#
#if (Test-Path -LiteralPath $outputPath -PathType Leaf) {
#    Write-Host "SKIP: output sudah ada:" -ForegroundColor Yellow
#    Write-Host $outputPath
#    exit 0
#}

# ============================================================
# Read source
# ============================================================

$content = Get-Content -LiteralPath $SourcePath -Raw -Encoding UTF8

$content = $content -replace "`r`n", "`n"
$content = $content -replace "`r", "`n"

$lines = $content -split "`n"

# ============================================================
# State
# ============================================================

$currentModule = $null
$lessonIndex = 0
$insideLessons = $false

$output = New-Object System.Collections.Generic.List[string]

# ============================================================
# Process
# ============================================================

foreach ($line in $lines) {

    # --------------------------------------------------------
    # Module
    # --------------------------------------------------------

    if ($line -match '^##\s+Module\s+(\d+)\b') {

        $currentModule = [int]$Matches[1]
        $lessonIndex = 0
        $insideLessons = $false

        $output.Add($line)
        continue
    }

    # --------------------------------------------------------
    # Lessons
    # --------------------------------------------------------

    if ($line -match '^Lessons:\s*$') {

        $insideLessons = $true

        $output.Add($line)
        continue
    }

    # --------------------------------------------------------
    # End of Lessons section
    # --------------------------------------------------------

    if ($insideLessons) {

        if (
            $line -match '^\s*(Mini Project|Status|Description):' -or
            $line -match '^##\s+'
        ) {
            $insideLessons = $false
        }
    }

    # --------------------------------------------------------
    # Lesson item
    # --------------------------------------------------------

    if (
        $insideLessons -and
        $null -ne $currentModule -and
        $line -match '^(\s*[\*\-]\s+\[[ xX]\]\s+)(?:\d+\.\d+\s+)?(.+?)\s*$'
    ) {

        $prefix = $Matches[1]
        $lessonTitle = $Matches[2].Trim()

        $lessonIndex++

        $number = "$currentModule.$lessonIndex"

        $output.Add("$prefix$number $lessonTitle")

        continue
    }

    # --------------------------------------------------------
    # Normal line
    # --------------------------------------------------------

    $output.Add($line)
}

# ============================================================
# Write output
# ============================================================

Set-Content `
    -LiteralPath $outputPath `
    -Value ($output -join "`n") `
    -Encoding UTF8

Write-Host ""
Write-Host "COURSE-numbered.md berhasil dibuat." -ForegroundColor Green
Write-Host "Output: $outputPath"