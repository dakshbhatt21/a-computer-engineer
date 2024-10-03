plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "com.acomputerengineer"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    viewBinding {
        enable = true
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    namespace = "com.acomputerengineer"
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.constraintlayout)
    implementation(libs.androidx.core)
    testImplementation(libs.junit)
    implementation(libs.picasso)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.exifinterface)
    implementation(libs.cardview)
    implementation(libs.recyclerview)
    implementation(libs.dexter)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.kotlin.stdlib)

    // Jetpack Compose dependencies
    implementation(libs.androidx.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)

    // for adding text on video
    implementation(libs.media3.transformer)
    implementation(libs.media3.effect)
    implementation(libs.media3.common)
    implementation(libs.androidx.media3.muxer)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
}

repositories {
    mavenCentral()
}
