# Quotes App

Browse original motivational practice quotes, choose another at random and share the displayed text through Android's system share sheet.

Pynexia Android Development / Week 2.

[Hosted showcase](https://engeyad.github.io/pynexia-week2-quotes/) · [PDF report](https://engeyad.github.io/pynexia-week2-quotes/downloads/Quotes-App-Report.pdf) · [APK](https://engeyad.github.io/pynexia-week2-quotes/downloads/Quotes-App.apk) · [Submission ZIP](https://engeyad.github.io/pynexia-week2-quotes/downloads/Quotes-App.zip)

## Implementation

### Collection and random selection

Twelve original practice quotes are stored in a string-array. Next chooses randomly from all other items, avoiding an immediate repeat.

### XML interface

TextView renders the quote and collection attribution. Buttons update the quote or open sharing. The screen scrolls on smaller displays.

### Persistence and Intents

SharedPreferences remembers the current quote. Saved view state handles recreation. ACTION_SEND with text/plain is wrapped in an Android chooser.

## Run

Open the repository root in Android Studio. Install SDK 35 and Build Tools 35.0.0, use JDK 17 or 21 and sync Gradle. Run `quotes` on Android 8.0/API 26 or newer. AGP 8.9.1; Gradle wrapper 8.11.1. Initial sync needs internet.

```powershell
.\gradlew.bat :quotes:assembleDebug :quotes:testDebugUnitTest :quotes:lintDebug
.\gradlew.bat :quotes:connectedDebugAndroidTest
```

The second command requires an emulator/device. On macOS/Linux use `chmod +x gradlew` and `./gradlew`.

## Walkthrough

1. Open the app and read the current thought.
2. Tap Next quote; the selected thought changes.
3. Rotate or close and reopen the app; the current thought is retained.
4. Tap Share quote and choose a destination in the system share sheet.

## Verification

3 JVM tests and 1 device tests passed with no failures, errors or skips. Lint: 0 errors and 47 warnings without suppression. Tested 2 November 2026 on Pixel 8 emulator, Android 17/API 37 (37.1 image), x86_64. No physical-device or API 26 run. See `verification/` for results with local paths redacted.

Random bounds and no immediate repeat across 1,000 selections; single/empty collection handling; UI next/recreation and exact shared Intent text.

## Scope

A local collection of original practice text, not quotations attributed to public figures. Sharing opens a chooser and never sends a message automatically. Available destinations depend on installed apps.

APKs are debug signed for evaluation. Hosted pages show actual screenshots and downloads, not Android emulation. No Pynexia form is submitted automatically.

## شرح عربي

يعرض التطبيق مجموعة من العبارات التحفيزية الأصلية المخصصة للتمرين. يختار زر التالي عبارة عشوائية مختلفة عن الحالية. يحتفظ التطبيق بالعبارة المعروضة عند إعادة فتحه أو تدوير الشاشة، ويفتح زر المشاركة نافذة المشاركة في Android بالنص الحالي.

## Submission

Copy `SUBMISSION-NOTES.txt` into the form. Attach the ZIP or PDF. APK is inside the ZIP because the earlier form does not list direct APK uploads. Verify the current Week 2 form accepts the same formats and limits.
