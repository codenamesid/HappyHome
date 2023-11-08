plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "om.happyhome.logging"
   lint{
       disable.add("EnsureInitializerMetadata")
   }
    defaultConfig {
        minSdk = 4
        compileSdk = 34
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation("androidx.startup:startup-runtime:1.1.1")
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("androidx.compose.runtime:runtime:1.5.4")
}