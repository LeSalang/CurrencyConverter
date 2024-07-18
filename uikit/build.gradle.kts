plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.lesa.uikit"
    compileSdk = libs.versions.androidSdk.compile.get().toInt()
    defaultConfig {
        minSdk = libs.versions.androidSdk.min.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = libs.versions.jvmTarget.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
}

dependencies {
    api(libs.androidx.material3)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.text.google.fonts)
    api(libs.androidx.ui.tooling.preview)
    api(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core.ktx)
}
