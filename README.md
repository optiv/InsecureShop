# 📱 InsecureShop

* InsecureShop is an Android application that is designed to be intentionally vulnerable. 
* The aim of creating this app is to teach developers and security professionals about the vulnerabilities that are present in modern Android applications. 
* This also serves as a platform to test your Android pentesting skills.
* Developed in Kotlin, this application was created primarily for research on Android Deeplinks and Webviews. However, several more vulnerabilities were added in this app which were found in real-world Android applications. The vulnerabilities present in this app are real and have been found during mobile pentests.

# ⚙️ Usage

You can compile the source code in Android Studio or simply download the APK file from [here](https://github.com/optiv/InsecureShop/releases/download/v1.0/InsecureShop.apk)

# 📌 Note:

* Rooted device is not required. All vulnerabilities can be exploited on a non-rooted device.
* No API's being used by the app.

# 🤔 How InsecureShop is different from other Damn Vulnerable Apps?

* **More Realistic:** Mimics a shopping Application.
* **Built-in Kotlin:** Just because most of the apps are now using Kotlin.
* **Contains Real-World Vulnerabilities:** Unlike other Damn Vulnerable Apps which contain hypothetical or unrealistic scenarios, most of the vulnerabilities in this app were recently found in actual pentest. Some of the vulnerable implementations are also taken which were highlighted in the research done by several security researchers.

# ❗️Vulnerabilities:

1. **Hardcoded Credentials:** Credentials are hardcoded somewhere that can be used to login to the application
2. **Insufficient URL Validation:** Possible to load any arbitrary URL in webview via Deeplink.
3. **Weak Host Validation Check:** Possible to bypass host validation check to load any arbitrary URL in webview.
4. **Arbitrary Code Execution:** Arbitrary Code Execution via third-party package contexts.
5. **Access to Protected Components:** The app takes an embedded Intent and passes it to method like startActivity. This allows any third party app to launch any protected component.
6. **Unprotected Data URIs:** The untrusted URI's passed via loadUrl method allows attackers to pass arbitrary URL in webview.
7. **Theft of Arbitrary:** Possible to steal files from app's local storage via ChooserActivity.
8. **Using Components with Known Vulnerabilities:** Identify the vulnerable components or libraries used in the app that can allow you to exfiltrate local files to remote domain.
9. **Insecure Broadcast Receiver:** An exported activity registers a broadcast during onCreate method execution. An attacker can trigger this broadcast and provide arbitrary URL in 'web_url' parameter.
10. **AWS Cognito Misconfiguration:** The misconfigured AWS cognito instance can be used to accesss AWS S3 bucket.
11. **Insecure use of FilePaths in FileProvider:** The use of wide file sharing declaration can be used to access root directory via content Provider.
12. **Use of Implicit intent to send a broadcast with sensitive data:** The use of Implicit intent can allow third-party apps to steal credentials.
13. **Intercepting Implicit intent to load arbitrary URL:** The use of Implicit intent can allow third-party apps to load any arbitrary URL in webview.
14. **Insecure Implementation of SetResult in exported Activity:** The insecure implementation used in ResultActivity can be used to access arbitrary content providers.
15. **Insecure Content Provider:** The content provider can be accessed by any third-party app to steal user credentials.
16. **Lack of SSL Certificate Validation:** The unsafe implementation of OnReceived SSL Error can be used to eavesdrop all the traffic loaded in webview.
17. **Insecure Webview Properties Enabled:** Insecure Webview properties are enabled that can allow third-party apps to exfiltrate local data to remote domain.
18. **Insecure Data Storage:** The app stores user credentials locally without encrypting them.
19. **Insecure Logging:** User credentials are leaked in logcat. Only attackers with physical access to the device can access this information. 

# 🕵 Hints:

The provided link doesn't provide you with solutions but can point you in the right direction 😉: 

https://docs.insecureshopapp.com 
(This is still under development)

# 🙌 Thanks:

* [Rujul Gandhi](https://www.linkedin.com/in/rujul-gandhi-3953337a/): Thank you for your contributions towards this app
* [Sergey Toshin](https://twitter.com/_bagipro) [(Oversecured)](https://oversecured.com): Thank you for your amazing research on Android security which prompted me to start this project
