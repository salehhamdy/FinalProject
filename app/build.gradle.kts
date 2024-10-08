plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    id("org.jetbrains.kotlin.plugin.serialization") version ("1.9.23")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.linkdev.finalproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.linkdev.finalproject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation ("androidx.compose.material:material-icons-extended:1.6.8")
    implementation ("androidx.compose.ui:ui:1.6.8")
    implementation ("androidx.compose.material:material:1.6.8")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.8")
    implementation("androidx.navigation:navigation-compose:2.8.0-beta06")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-alpha03")
    implementation ("androidx.activity:activity-compose:1.3.1")
    implementation ("androidx.compose.ui:ui-tooling:1.6.8")
    implementation ("androidx.compose.foundation:foundation:1.6.8")
    implementation ("androidx.compose.foundation:foundation-layout:1.6.8")
    implementation ("androidx.compose.runtime:runtime:1.6.8")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
    implementation ("com.google.dagger:hilt-android:2.50")
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.room.common)
    kapt ("com.google.dagger:hilt-android-compiler:2.50")
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")
    implementation("com.squareup:javapoet:1.13.0") // <-- added this
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.50")
    implementation ("androidx.camera:camera-camera2:1.1.0-alpha11")
    implementation ("androidx.camera:camera-lifecycle:1.1.0-alpha11")
    implementation ("androidx.camera:camera-view:1.0.0-alpha27")
    implementation ("androidx.camera:camera-extensions:1.0.0-alpha27")
    implementation ("io.coil-kt:coil-compose:2.2.2")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
kapt {
    correctErrorTypes = true
}