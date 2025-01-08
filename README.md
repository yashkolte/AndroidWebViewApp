# Android WebView Application

This repository contains the source code for an Android application that displays a web-based app using a WebView. The application loads and renders a website directly within the Android app.

## Features
- Displays a web-based app inside the Android app.
- Enables JavaScript support for better web page functionality.
- Uses `WebViewClient` to ensure in-app browsing.
- Implements Edge-to-Edge screen design.

## Prerequisites
- Android Studio installed on your system.
- A device or emulator running Android 5.0 (API level 21) or higher.

## Code Explanation
The main functionality is implemented in the `MainActivity.java` file:

### Key Components
- **`WebView`**: A view that displays web pages.
- **`WebViewClient`**: Handles page navigation inside the app instead of opening external browsers.
- **`EdgeToEdge`**: Enables an edge-to-edge layout for modern Android UI design.

### Code Walkthrough
```java
package com.yashkolte.vasttra;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    WebView myWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize WebView
        myWeb = findViewById(R.id.myWeb);
        myWeb.getSettings().setJavaScriptEnabled(true); // Enable JavaScript
        myWeb.setWebViewClient(new WebViewClient()); // Use WebViewClient for in-app browsing
        myWeb.loadUrl("https://vasttram.com/"); // Load the website
    }
}
```

### Layout File (`activity_main.xml`)
Ensure your layout file contains a `WebView` with the ID `myWeb`:
```xml
<WebView
    android:id="@+id/myWeb"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

## How to Run
1. Clone or download the repository.
2. Open the project in Android Studio.
3. Build the project to ensure all dependencies are installed.
4. Run the app on a physical device or emulator.

## Customization
- **Change the URL**: To load a different website, replace `https://vasttram.com/` in the `loadUrl` method with your desired URL.
- **Enable Additional Settings**: Modify WebView settings (e.g., enabling zoom, handling file uploads) as needed.

## Notes
- Ensure the website being loaded supports mobile browsers for the best user experience.
- Add necessary permissions in `AndroidManifest.xml` if the website requires special permissions (e.g., Internet access):

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## License
This project is licensed under the [MIT License](LICENSE). Feel free to use and modify the code as per your requirements.

