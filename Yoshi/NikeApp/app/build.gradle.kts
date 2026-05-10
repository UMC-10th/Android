import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    id("kotlin-kapt")
}

// local.properties 읽기 (깃에 올라가지 않는 파일)
val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        load(FileInputStream(file))
    }
}
val nikeApiKey: String = localProperties.getProperty("NIKE_API_KEY") ?: ""

android {
    namespace = "com.example.NikeApp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.NikeApp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // BuildConfig.NIKE_API_KEY 로 코드에서 접근 가능
        buildConfigField("String", "NIKE_API_KEY", "\"$nikeApiKey\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.google.android.material:material:1.12.0")
    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // Gson
    implementation("com.google.code.gson:gson:2.10.1")
    // Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // OkHttp
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // Fragment KTX (by viewModels())
    implementation(libs.androidx.fragment.ktx)
    // Lifecycle ViewModel KTX (viewModelScope)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
}