plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.korlin.args)
}

android {
    namespace = "com.shpzdsh.musicapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.shpzdsh.musicapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.navigation.fragment)

    implementation(libs.retrofit)
    implementation(libs.gson.converter)

    implementation(libs.koin.android)

    implementation(libs.popup)

    implementation(libs.coil.android)
    implementation(libs.coil.network)

    implementation(libs.exoplayer.ui)
    implementation(libs.exoplayer.base)
    implementation(libs.exoplayer.session)
    implementation(libs.media)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}