## Android static analysis

72 finding(s) after policy and baseline filtering.

| Severity | Rule | Location | Summary |
| --- | --- | --- | --- |
| critical | `RemoveWorkManagerInitializer` | `app/src/main/AndroidManifest.xml:7` | Android Lint points to androidx.work.WorkManagerInitializer in the manifest file at line 7, indicating it should be removed when using on-demand initialization. |
| high | `ANDROID-SEC-001` | `app/src/androidTest/java/com/example/ecommerceapp/ExampleInstrumentedTest.kt:14` | The cleartext URL occurs in an instrumentation test file inside a comment/documentation reference pointing to testing documentation, which is a non-production test case. |
| medium | `yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag` | `.github/workflows/android-static-analysis.yml:17` | Static analysis indicates that line 17 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA. |
| medium | `yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag` | `.github/workflows/android-static-analysis.yml:21` | Static analysis indicates that line 21 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA. |
| medium | `yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag` | `.github/workflows/android-static-analysis.yml:26` | Static analysis indicates that line 26 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA. |
| medium | `yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag` | `.github/workflows/android-static-analysis.yml:33` | Static analysis indicates that line 33 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA. |
| medium | `yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag` | `.github/workflows/android-static-analysis.yml:41` | Static analysis indicates that line 41 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA. |
| medium | `yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag` | `.github/workflows/android-static-analysis.yml:46` | Static analysis indicates that line 46 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA. |
| medium | `gcp-api-key` | `app/build.gradle.kts:47` | Secret detection identified a potential GCP API key in app/build.gradle.kts at line 47. |
| medium | `java.android.security.exported_activity.exported_activity` | `app/src/main/AndroidManifest.xml:17` | Line 17 of AndroidManifest.xml marks an activity as exported, allowing other applications on the device to launch it. |
| medium | `RedundantLabel` | `app/src/main/AndroidManifest.xml:20` | Line 20 of AndroidManifest.xml contains a redundant label that can be safely removed. |
| medium | `ANDROID-QUALITY-001` | `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:19` | Line 19 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code. |
| medium | `ANDROID-QUALITY-001` | `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:33` | Line 33 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code. |
| medium | `ANDROID-QUALITY-001` | `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:38` | Line 38 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code. |
| medium | `ANDROID-QUALITY-001` | `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:42` | Line 42 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code. |
| medium | `ANDROID-QUALITY-001` | `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:47` | Line 47 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code. |
| medium | `ANDROID-QUALITY-001` | `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:52` | Line 52 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code. |
| medium | `ANDROID-QUALITY-001` | `app/src/main/java/com/example/ecommerceapp/data/repository/DefaultShopRepository.kt:58` | Line 58 of DefaultShopRepository.kt contains a Log.d call recording catalog refresh information. |
| medium | `ANDROID-QUALITY-001` | `app/src/main/java/com/example/ecommerceapp/data/repository/DefaultShopRepository.kt:61` | Line 61 of DefaultShopRepository.kt contains a Log.w call recording refresh failures. |
| medium | `ANDROID-QUALITY-001` | `app/src/main/java/com/example/ecommerceapp/data/sync/CatalogSyncService.kt:24` | Line 24 of CatalogSyncService.kt contains a Log.d call indicating service refresh started. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/shop/ShopApp.kt:601` | Line 601 of ShopApp.kt assigns contentAlignment inside a Box modifier that may be unreferenced. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/shop/ShopApp.kt:614` | Line 614 of ShopApp.kt assigns key in items list generation. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/shop/ShopViewModel.kt:63` | Line 63 of ShopViewModel.kt assigns ignoreCase in description contains evaluation. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:6` | Line 6 of Color.kt defines PrimaryLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:7` | Line 7 of Color.kt defines OnPrimaryLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:8` | Line 8 of Color.kt defines PrimaryContainerLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:9` | Line 9 of Color.kt defines OnPrimaryContainerLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:11` | Line 11 of Color.kt defines SecondaryLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:12` | Line 12 of Color.kt defines OnSecondaryLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:13` | Line 13 of Color.kt defines SecondaryContainerLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:14` | Line 14 of Color.kt defines OnSecondaryContainerLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:16` | Line 16 of Color.kt defines TertiaryLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:17` | Line 17 of Color.kt defines OnTertiaryLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:18` | Line 18 of Color.kt defines TertiaryContainerLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:19` | Line 19 of Color.kt defines OnTertiaryContainerLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:21` | Line 21 of Color.kt defines BackgroundLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:22` | Line 22 of Color.kt defines OnBackgroundLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:23` | Line 23 of Color.kt defines SurfaceLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:24` | Line 24 of Color.kt defines OnSurfaceLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:25` | Line 25 of Color.kt defines SurfaceVariantLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:26` | Line 26 of Color.kt defines OnSurfaceVariantLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:27` | Line 27 of Color.kt defines OutlineLight which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:30` | Line 30 of Color.kt defines PrimaryDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:31` | Line 31 of Color.kt defines OnPrimaryDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:32` | Line 32 of Color.kt defines PrimaryContainerDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:33` | Line 33 of Color.kt defines OnPrimaryContainerDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:35` | Line 35 of Color.kt defines SecondaryDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:36` | Line 36 of Color.kt defines OnSecondaryDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:37` | Line 37 of Color.kt defines SecondaryContainerDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:38` | Line 38 of Color.kt defines OnSecondaryContainerDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:40` | Line 40 of Color.kt defines TertiaryDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:41` | Line 41 of Color.kt defines OnTertiaryDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:42` | Line 42 of Color.kt defines TertiaryContainerDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:43` | Line 43 of Color.kt defines OnTertiaryContainerDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:45` | Line 45 of Color.kt defines BackgroundDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:46` | Line 46 of Color.kt defines OnBackgroundDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:47` | Line 47 of Color.kt defines SurfaceDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:48` | Line 48 of Color.kt defines OnSurfaceDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:49` | Line 49 of Color.kt defines SurfaceVariantDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:50` | Line 50 of Color.kt defines OnSurfaceVariantDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:51` | Line 51 of Color.kt defines OutlineDark which is flagged as an unused local variable. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Theme.kt:65` | Line 65 of Theme.kt defines dynamicColor parameter which is flagged as unreferenced. |
| medium | `ANDROID-QUALITY-003` | `app/src/main/java/com/example/ecommerceapp/ui/theme/Type.kt:9` | Line 9 of Type.kt defines Typography which is flagged as an unused variable. |
| medium | `UnusedResources` | `app/src/main/res/values/colors.xml:3` | Line 3 of colors.xml defines R.color.purple_200 which is flagged as unused. |
| medium | `UnusedResources` | `app/src/main/res/values/colors.xml:4` | Line 4 of colors.xml defines R.color.purple_500 which is flagged as unused. |
| medium | `UnusedResources` | `app/src/main/res/values/colors.xml:5` | Line 5 of colors.xml defines R.color.purple_700 which is flagged as unused. |
| medium | `UnusedResources` | `app/src/main/res/values/colors.xml:6` | Line 6 of colors.xml defines R.color.teal_200 which is flagged as unused. |
| medium | `UnusedResources` | `app/src/main/res/values/colors.xml:7` | Line 7 of colors.xml defines R.color.teal_700 which is flagged as unused. |
| medium | `UnusedResources` | `app/src/main/res/values/colors.xml:8` | Line 8 of colors.xml defines R.color.black which is flagged as unused. |
| medium | `UnusedResources` | `app/src/main/res/values/colors.xml:9` | Line 9 of colors.xml defines R.color.white which is flagged as unused. |
| medium | `GradleDependency` | `gradle/libs.versions.toml:6` | Line 6 of gradle/libs.versions.toml indicates a newer version of dev.chrisbanes.haze:haze is available. |
| medium | `AndroidGradlePluginVersion` | `gradle/wrapper/gradle-wrapper.properties:3` | Line 3 of gradle/wrapper/gradle-wrapper.properties indicates a newer version of Gradle is available. |

## Finding details

### [CRITICAL] RemoveWorkManagerInitializer — Android Lint finding
**Location:** `app/src/main/AndroidManifest.xml:7`  
**Source:** Android Lint+Gemini · confidence: high · triage: confirmed

**Finding:** Android Lint points to androidx.work.WorkManagerInitializer in the manifest file at line 7, indicating it should be removed when using on-demand initialization.

**Recommended action:** If an `android.app.Application` implements `androidx.work.Configuration.Provider`,
the default `androidx.startup.InitializationProvider` needs to be removed from the
AndroidManifest.xml file.

**Gemini triage reason:** The manifest references WorkManagerInitializer requiring manual removal for on-demand initialization.

### [HIGH] ANDROID-SEC-001 — Cleartext HTTP URL
**Location:** `app/src/androidTest/java/com/example/ecommerceapp/ExampleInstrumentedTest.kt:14`  
**Source:** built-in+Gemini · confidence: high · triage: likely_false_positive

**Finding:** The cleartext URL occurs in an instrumentation test file inside a comment/documentation reference pointing to testing documentation, which is a non-production test case.

**Recommended action:** Replace the endpoint with HTTPS. If HTTP is required for local development, put it in a debug-only configuration and document the exception.

**Gemini triage reason:** The finding is located in an androidTest file and references a documentation link.

**Evidence:**

```text
* See [testing documentation](http://d.android.com/tools/testing).
```

### [MEDIUM] yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag — yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag
**Location:** `.github/workflows/android-static-analysis.yml:17`  
**Source:** Semgrep+Gemini · confidence: high · triage: confirmed

**Finding:** Static analysis indicates that line 17 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA.

**Recommended action:** Review the Semgrep rule and remediate the issue.

**Gemini triage reason:** The workflow step uses a mutable action reference.

**Evidence:**

```text
requires login
```

### [MEDIUM] yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag — yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag
**Location:** `.github/workflows/android-static-analysis.yml:21`  
**Source:** Semgrep+Gemini · confidence: high · triage: confirmed

**Finding:** Static analysis indicates that line 21 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA.

**Recommended action:** Review the Semgrep rule and remediate the issue.

**Gemini triage reason:** The workflow step uses a mutable action reference.

**Evidence:**

```text
requires login
```

### [MEDIUM] yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag — yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag
**Location:** `.github/workflows/android-static-analysis.yml:26`  
**Source:** Semgrep+Gemini · confidence: high · triage: confirmed

**Finding:** Static analysis indicates that line 26 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA.

**Recommended action:** Review the Semgrep rule and remediate the issue.

**Gemini triage reason:** The workflow step uses a mutable action reference.

**Evidence:**

```text
requires login
```

### [MEDIUM] yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag — yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag
**Location:** `.github/workflows/android-static-analysis.yml:33`  
**Source:** Semgrep+Gemini · confidence: high · triage: confirmed

**Finding:** Static analysis indicates that line 33 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA.

**Recommended action:** Review the Semgrep rule and remediate the issue.

**Gemini triage reason:** The workflow step uses a mutable action reference.

**Evidence:**

```text
requires login
```

### [MEDIUM] yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag — yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag
**Location:** `.github/workflows/android-static-analysis.yml:41`  
**Source:** Semgrep+Gemini · confidence: high · triage: confirmed

**Finding:** Static analysis indicates that line 41 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA.

**Recommended action:** Review the Semgrep rule and remediate the issue.

**Gemini triage reason:** The workflow step uses a mutable action reference.

**Evidence:**

```text
requires login
```

### [MEDIUM] yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag — yaml.github-actions.security.github-actions-mutable-action-tag.github-actions-mutable-action-tag
**Location:** `.github/workflows/android-static-analysis.yml:46`  
**Source:** Semgrep+Gemini · confidence: high · triage: confirmed

**Finding:** Static analysis indicates that line 46 of the GitHub Actions workflow uses a mutable reference instead of a pinned commit SHA.

**Recommended action:** Review the Semgrep rule and remediate the issue.

**Gemini triage reason:** The workflow step uses a mutable action reference.

**Evidence:**

```text
requires login
```

### [MEDIUM] gcp-api-key — gcp-api-key has detected secret for file app/build.gradle.kts at commit df446a008088f94132ad4a97fd58c2ed7f687ee4.
**Location:** `app/build.gradle.kts:47`  
**Source:** gitleaks+Gemini · confidence: high · triage: confirmed

**Finding:** Secret detection identified a potential GCP API key in app/build.gradle.kts at line 47.

**Recommended action:** Review the external analyzer documentation and remediate the finding.

**Gemini triage reason:** A potential GCP API key secret has been detected in build configuration.

### [MEDIUM] java.android.security.exported_activity.exported_activity — java.android.security.exported_activity.exported_activity
**Location:** `app/src/main/AndroidManifest.xml:17`  
**Source:** Semgrep+Gemini · confidence: high · triage: confirmed

**Finding:** Line 17 of AndroidManifest.xml marks an activity as exported, allowing other applications on the device to launch it.

**Recommended action:** Review the Semgrep rule and remediate the issue.

**Gemini triage reason:** The manifest exports an activity which can be accessed by other applications.

**Evidence:**

```text
requires login
```

### [MEDIUM] RedundantLabel — Android Lint finding
**Location:** `app/src/main/AndroidManifest.xml:20`  
**Source:** Android Lint+Gemini · confidence: high · triage: confirmed

**Finding:** Line 20 of AndroidManifest.xml contains a redundant label that can be safely removed.

**Recommended action:** When an activity does not have a label attribute, it will use the one from the application tag. Since the application has already specified the same label, the label on this activity can be omitted.

**Gemini triage reason:** Android Lint detected a redundant label in the manifest.

### [MEDIUM] ANDROID-QUALITY-001 — Logging call requires review
**Location:** `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:19`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 19 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code.

**Recommended action:** Remove the log, redact sensitive values, or guard it behind a debug-only build configuration.

**Gemini triage reason:** Production code contains a debug logging call.

**Evidence:**

```text
Log.d(TAG, "onCreate")
```

### [MEDIUM] ANDROID-QUALITY-001 — Logging call requires review
**Location:** `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:33`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 33 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code.

**Recommended action:** Remove the log, redact sensitive values, or guard it behind a debug-only build configuration.

**Gemini triage reason:** Production code contains a debug logging call.

**Evidence:**

```text
Log.d(TAG, "onStart")
```

### [MEDIUM] ANDROID-QUALITY-001 — Logging call requires review
**Location:** `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:38`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 38 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code.

**Recommended action:** Remove the log, redact sensitive values, or guard it behind a debug-only build configuration.

**Gemini triage reason:** Production code contains a debug logging call.

**Evidence:**

```text
Log.d(TAG, "onResume")
```

### [MEDIUM] ANDROID-QUALITY-001 — Logging call requires review
**Location:** `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:42`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 42 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code.

**Recommended action:** Remove the log, redact sensitive values, or guard it behind a debug-only build configuration.

**Gemini triage reason:** Production code contains a debug logging call.

**Evidence:**

```text
Log.d(TAG, "onPause")
```

### [MEDIUM] ANDROID-QUALITY-001 — Logging call requires review
**Location:** `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:47`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 47 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code.

**Recommended action:** Remove the log, redact sensitive values, or guard it behind a debug-only build configuration.

**Gemini triage reason:** Production code contains a debug logging call.

**Evidence:**

```text
Log.d(TAG, "onStop")
```

### [MEDIUM] ANDROID-QUALITY-001 — Logging call requires review
**Location:** `app/src/main/java/com/example/ecommerceapp/MainActivity.kt:52`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 52 of MainActivity.kt contains a Log.d call which logs lifecycle events in production code.

**Recommended action:** Remove the log, redact sensitive values, or guard it behind a debug-only build configuration.

**Gemini triage reason:** Production code contains a debug logging call.

**Evidence:**

```text
Log.d(TAG, "onDestroy")
```

### [MEDIUM] ANDROID-QUALITY-001 — Logging call requires review
**Location:** `app/src/main/java/com/example/ecommerceapp/data/repository/DefaultShopRepository.kt:58`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 58 of DefaultShopRepository.kt contains a Log.d call recording catalog refresh information.

**Recommended action:** Remove the log, redact sensitive values, or guard it behind a debug-only build configuration.

**Gemini triage reason:** Production repository code contains a debug logging call.

**Evidence:**

```text
Log.d(TAG, "Catalog refreshed from $source with ${remoteProducts.size} items")
```

### [MEDIUM] ANDROID-QUALITY-001 — Logging call requires review
**Location:** `app/src/main/java/com/example/ecommerceapp/data/repository/DefaultShopRepository.kt:61`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 61 of DefaultShopRepository.kt contains a Log.w call recording refresh failures.

**Recommended action:** Remove the log, redact sensitive values, or guard it behind a debug-only build configuration.

**Gemini triage reason:** Production repository code contains a warning logging call.

**Evidence:**

```text
Log.w(TAG, "Refresh failed from $source, keeping cached data", error)
```

### [MEDIUM] ANDROID-QUALITY-001 — Logging call requires review
**Location:** `app/src/main/java/com/example/ecommerceapp/data/sync/CatalogSyncService.kt:24`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 24 of CatalogSyncService.kt contains a Log.d call indicating service refresh started.

**Recommended action:** Remove the log, redact sensitive values, or guard it behind a debug-only build configuration.

**Gemini triage reason:** Production sync service contains a debug logging call.

**Evidence:**

```text
Log.d(TAG, "Service refresh started")
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/shop/ShopApp.kt:601`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 601 of ShopApp.kt assigns contentAlignment inside a Box modifier that may be unreferenced.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A variable or parameter assignment is identified as potentially unused.

**Evidence:**

```text
Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/shop/ShopApp.kt:614`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 614 of ShopApp.kt assigns key in items list generation.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A variable or parameter assignment is identified as potentially unused.

**Evidence:**

```text
items(sortedItems, key = { it.product.id }) { item ->
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/shop/ShopViewModel.kt:63`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 63 of ShopViewModel.kt assigns ignoreCase in description contains evaluation.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A variable or parameter assignment is identified as potentially unused.

**Evidence:**

```text
product.description.contains(query, ignoreCase = true)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:6`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 6 of Color.kt defines PrimaryLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val PrimaryLight = Color(0xFF006B5E)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:7`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 7 of Color.kt defines OnPrimaryLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnPrimaryLight = Color(0xFFFFFFFF)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:8`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 8 of Color.kt defines PrimaryContainerLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val PrimaryContainerLight = Color(0xFF76F8E1)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:9`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 9 of Color.kt defines OnPrimaryContainerLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnPrimaryContainerLight = Color(0xFF00201B)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:11`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 11 of Color.kt defines SecondaryLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val SecondaryLight = Color(0xFF4A635F)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:12`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 12 of Color.kt defines OnSecondaryLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnSecondaryLight = Color(0xFFFFFFFF)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:13`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 13 of Color.kt defines SecondaryContainerLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val SecondaryContainerLight = Color(0xFFCDE8E2)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:14`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 14 of Color.kt defines OnSecondaryContainerLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnSecondaryContainerLight = Color(0xFF06201C)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:16`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 16 of Color.kt defines TertiaryLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val TertiaryLight = Color(0xFF446279)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:17`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 17 of Color.kt defines OnTertiaryLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnTertiaryLight = Color(0xFFFFFFFF)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:18`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 18 of Color.kt defines TertiaryContainerLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val TertiaryContainerLight = Color(0xFFCBE6FF)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:19`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 19 of Color.kt defines OnTertiaryContainerLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnTertiaryContainerLight = Color(0xFF001E30)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:21`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 21 of Color.kt defines BackgroundLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val BackgroundLight = Color(0xFFF4FBF9)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:22`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 22 of Color.kt defines OnBackgroundLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnBackgroundLight = Color(0xFF191C1B)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:23`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 23 of Color.kt defines SurfaceLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val SurfaceLight = Color(0xFFF4FBF9)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:24`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 24 of Color.kt defines OnSurfaceLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnSurfaceLight = Color(0xFF191C1B)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:25`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 25 of Color.kt defines SurfaceVariantLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val SurfaceVariantLight = Color(0xFFDAE5E1)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:26`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 26 of Color.kt defines OnSurfaceVariantLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnSurfaceVariantLight = Color(0xFF3F4946)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:27`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 27 of Color.kt defines OutlineLight which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OutlineLight = Color(0xFF6F7976)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:30`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 30 of Color.kt defines PrimaryDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val PrimaryDark = Color(0xFF56DBC5)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:31`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 31 of Color.kt defines OnPrimaryDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnPrimaryDark = Color(0xFF003730)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:32`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 32 of Color.kt defines PrimaryContainerDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val PrimaryContainerDark = Color(0xFF005046)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:33`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 33 of Color.kt defines OnPrimaryContainerDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnPrimaryContainerDark = Color(0xFF76F8E1)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:35`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 35 of Color.kt defines SecondaryDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val SecondaryDark = Color(0xFFB1CCC6)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:36`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 36 of Color.kt defines OnSecondaryDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnSecondaryDark = Color(0xFF1C3531)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:37`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 37 of Color.kt defines SecondaryContainerDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val SecondaryContainerDark = Color(0xFF334B47)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:38`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 38 of Color.kt defines OnSecondaryContainerDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnSecondaryContainerDark = Color(0xFFCDE8E2)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:40`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 40 of Color.kt defines TertiaryDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val TertiaryDark = Color(0xFFACCAE5)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:41`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 41 of Color.kt defines OnTertiaryDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnTertiaryDark = Color(0xFF133348)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:42`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 42 of Color.kt defines TertiaryContainerDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val TertiaryContainerDark = Color(0xFF2C4A60)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:43`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 43 of Color.kt defines OnTertiaryContainerDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnTertiaryContainerDark = Color(0xFFCBE6FF)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:45`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 45 of Color.kt defines BackgroundDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val BackgroundDark = Color(0xFF0F1413)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:46`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 46 of Color.kt defines OnBackgroundDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnBackgroundDark = Color(0xFFE0E3E1)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:47`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 47 of Color.kt defines SurfaceDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val SurfaceDark = Color(0xFF0F1413)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:48`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 48 of Color.kt defines OnSurfaceDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnSurfaceDark = Color(0xFFE0E3E1)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:49`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 49 of Color.kt defines SurfaceVariantDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val SurfaceVariantDark = Color(0xFF3F4946)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:50`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 50 of Color.kt defines OnSurfaceVariantDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OnSurfaceVariantDark = Color(0xFFBEC9C5)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Color.kt:51`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 51 of Color.kt defines OutlineDark which is flagged as an unused local variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A color theme variable is reported as unused.

**Evidence:**

```text
val OutlineDark = Color(0xFF89938F)
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Theme.kt:65`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 65 of Theme.kt defines dynamicColor parameter which is flagged as unreferenced.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A parameter assignment is flagged as potentially unused.

**Evidence:**

```text
dynamicColor: Boolean = true, // Enabled by default
```

### [MEDIUM] ANDROID-QUALITY-003 — Potential unused local variable
**Location:** `app/src/main/java/com/example/ecommerceapp/ui/theme/Type.kt:9`  
**Source:** built-in+Gemini · confidence: high · triage: confirmed

**Finding:** Line 9 of Type.kt defines Typography which is flagged as an unused variable.

**Recommended action:** Remove it, use it, or suppress this finding when a framework accesses it indirectly.

**Gemini triage reason:** A typography definition is reported as unused.

**Evidence:**

```text
val Typography = Typography(
```

### [MEDIUM] UnusedResources — Android Lint finding
**Location:** `app/src/main/res/values/colors.xml:3`  
**Source:** Android Lint+Gemini · confidence: high · triage: confirmed

**Finding:** Line 3 of colors.xml defines R.color.purple_200 which is flagged as unused.

**Recommended action:** Unused resources make applications larger and slow down builds.


The unused resource check can ignore tests. If you want to include resources that are only referenced from tests, consider packaging them in a test source set instead.

You can include test sources in the unused resource check by setting the system property `lint.unused-resources.include-tests=true`, and to exclude them (usually for performance reasons), use `lint.unused-resources.exclude-tests=true`.


**Gemini triage reason:** Android Lint reports the color resource is unused.

### [MEDIUM] UnusedResources — Android Lint finding
**Location:** `app/src/main/res/values/colors.xml:4`  
**Source:** Android Lint+Gemini · confidence: high · triage: confirmed

**Finding:** Line 4 of colors.xml defines R.color.purple_500 which is flagged as unused.

**Recommended action:** Unused resources make applications larger and slow down builds.


The unused resource check can ignore tests. If you want to include resources that are only referenced from tests, consider packaging them in a test source set instead.

You can include test sources in the unused resource check by setting the system property `lint.unused-resources.include-tests=true`, and to exclude them (usually for performance reasons), use `lint.unused-resources.exclude-tests=true`.


**Gemini triage reason:** Android Lint reports the color resource is unused.

### [MEDIUM] UnusedResources — Android Lint finding
**Location:** `app/src/main/res/values/colors.xml:5`  
**Source:** Android Lint+Gemini · confidence: high · triage: confirmed

**Finding:** Line 5 of colors.xml defines R.color.purple_700 which is flagged as unused.

**Recommended action:** Unused resources make applications larger and slow down builds.


The unused resource check can ignore tests. If you want to include resources that are only referenced from tests, consider packaging them in a test source set instead.

You can include test sources in the unused resource check by setting the system property `lint.unused-resources.include-tests=true`, and to exclude them (usually for performance reasons), use `lint.unused-resources.exclude-tests=true`.


**Gemini triage reason:** Android Lint reports the color resource is unused.

### [MEDIUM] UnusedResources — Android Lint finding
**Location:** `app/src/main/res/values/colors.xml:6`  
**Source:** Android Lint+Gemini · confidence: high · triage: confirmed

**Finding:** Line 6 of colors.xml defines R.color.teal_200 which is flagged as unused.

**Recommended action:** Unused resources make applications larger and slow down builds.


The unused resource check can ignore tests. If you want to include resources that are only referenced from tests, consider packaging them in a test source set instead.

You can include test sources in the unused resource check by setting the system property `lint.unused-resources.include-tests=true`, and to exclude them (usually for performance reasons), use `lint.unused-resources.exclude-tests=true`.


**Gemini triage reason:** Android Lint reports the color resource is unused.

### [MEDIUM] UnusedResources — Android Lint finding
**Location:** `app/src/main/res/values/colors.xml:7`  
**Source:** Android Lint+Gemini · confidence: high · triage: confirmed

**Finding:** Line 7 of colors.xml defines R.color.teal_700 which is flagged as unused.

**Recommended action:** Unused resources make applications larger and slow down builds.


The unused resource check can ignore tests. If you want to include resources that are only referenced from tests, consider packaging them in a test source set instead.

You can include test sources in the unused resource check by setting the system property `lint.unused-resources.include-tests=true`, and to exclude them (usually for performance reasons), use `lint.unused-resources.exclude-tests=true`.


**Gemini triage reason:** Android Lint reports the color resource is unused.

### [MEDIUM] UnusedResources — Android Lint finding
**Location:** `app/src/main/res/values/colors.xml:8`  
**Source:** Android Lint+Gemini · confidence: high · triage: confirmed

**Finding:** Line 8 of colors.xml defines R.color.black which is flagged as unused.

**Recommended action:** Unused resources make applications larger and slow down builds.


The unused resource check can ignore tests. If you want to include resources that are only referenced from tests, consider packaging them in a test source set instead.

You can include test sources in the unused resource check by setting the system property `lint.unused-resources.include-tests=true`, and to exclude them (usually for performance reasons), use `lint.unused-resources.exclude-tests=true`.


**Gemini triage reason:** Android Lint reports the color resource is unused.

### [MEDIUM] UnusedResources — Android Lint finding
**Location:** `app/src/main/res/values/colors.xml:9`  
**Source:** Android Lint+Gemini · confidence: high · triage: confirmed

**Finding:** Line 9 of colors.xml defines R.color.white which is flagged as unused.

**Recommended action:** Unused resources make applications larger and slow down builds.


The unused resource check can ignore tests. If you want to include resources that are only referenced from tests, consider packaging them in a test source set instead.

You can include test sources in the unused resource check by setting the system property `lint.unused-resources.include-tests=true`, and to exclude them (usually for performance reasons), use `lint.unused-resources.exclude-tests=true`.


**Gemini triage reason:** Android Lint reports the color resource is unused.

### [MEDIUM] GradleDependency — Android Lint finding
**Location:** `gradle/libs.versions.toml:6`  
**Source:** Android Lint+Gemini · confidence: high · triage: confirmed

**Finding:** Line 6 of gradle/libs.versions.toml indicates a newer version of dev.chrisbanes.haze:haze is available.

**Recommended action:** This detector looks for usages of libraries where the version you are using is not the current stable release. Using older versions is fine, and there are cases where you deliberately want to stick with an older version. However, you may simply not be aware that a more recent version is available, and that is what this lint check helps find.

**Gemini triage reason:** Android Lint notes a newer dependency version is available.

### [MEDIUM] AndroidGradlePluginVersion — Android Lint finding
**Location:** `gradle/wrapper/gradle-wrapper.properties:3`  
**Source:** Android Lint+Gemini · confidence: high · triage: confirmed

**Finding:** Line 3 of gradle/wrapper/gradle-wrapper.properties indicates a newer version of Gradle is available.

**Recommended action:** This detector looks for usage of the Android Gradle Plugin where the version you are using is not the current stable release. Using older versions is fine, and there are cases where you deliberately want to stick with an older version. However, you may simply not be aware that a more recent version is available, and that is what this lint check helps find.

**Gemini triage reason:** Android Lint notes a newer Gradle version is available.

Warnings:
- Detekt skipped: Gradle Detekt task is not configured in this Android project.
