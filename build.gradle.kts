plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
<<<<<<< HEAD
    id ("kotlin-android")

}

android {
    namespace = "tn.esprit.payment"
    compileSdk = 34
    viewBinding { enable = true }



    defaultConfig {
        applicationId = "tn.esprit.payment"
        minSdk = 24
        targetSdk = 33
=======
    //kotlin("android")
}

android {
    namespace = "com.example.stustay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.stustay"
        minSdk = 24
        targetSdk = 34
>>>>>>> 86cdfde0d8ceebcf89d007985ea5d2bbf16ed6a9
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

<<<<<<< HEAD
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("junit:junit:4.13.2")
    implementation ("org.json:json:20210307")
    implementation ("com.google.code.gson:gson:2.8.9")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

=======
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
   // implementation ("com.github.bumptech.glide:glide:4.12.0")
   // kapt ("com.github.bumptech.glide:compiler:4.12.0")
>>>>>>> 86cdfde0d8ceebcf89d007985ea5d2bbf16ed6a9


}