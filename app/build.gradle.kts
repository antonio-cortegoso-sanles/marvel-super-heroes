plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        applicationId = "es.plexus.android.marvelsuperheroes"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = CodeVersion.code
        versionName = CodeVersion.name

        multiDexEnabled  = true
    }

    signingConfigs {
        register("release") {
        }
    }
    buildTypes {
        named("release").configure {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    sourceSets {
        getByName("main") { java.srcDir("src/main/kotlin") }
    }
    lintOptions {
        isAbortOnError = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.anko)
    implementation(Libraries.multidex)
    implementation(Libraries.googleMaterial)
    implementation(Libraries.dxCore)
    implementation(Libraries.appCompat)

    // other modules
    implementation(project(":presentation-layer"))
    implementation(project(":domain-layer"))
    implementation(project(":data-layer"))

    // 3rd party libraries
    implementation(Libraries.koinAndroid)
}