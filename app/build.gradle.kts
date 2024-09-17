plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    //id("com.google.gms.google-services")
}

android {
    namespace = "com.example.resturant"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.resturant"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding= true
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.gson)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.glide)


    // When using the BoM, you don't specify versions in Firebase library dependencies


    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}