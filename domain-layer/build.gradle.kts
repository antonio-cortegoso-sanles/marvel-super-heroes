plugins {
    id (Plugins.androidLibrary)

    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtensions)
    kotlin(Plugins.kotlinKapt)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = CodeVersion.code
        versionName = CodeVersion.name

        multiDexEnabled  = true

        testInstrumentationRunner = Libraries.testRunner
    }

    buildTypes {
        named("release").configure {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        getByName("main") { java.srcDir("src/main/kotlin") }
        getByName("test") { java.srcDir("src/test/kotlin") }
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
    implementation(Libraries.kotlinCoroutinesCore)
    implementation(Libraries.kotlinCoroutinesAndroid)
    // 3rd party libraries
    implementation(Libraries.koinAndroid)
    api(Libraries.arrowCore)
    api(Libraries.arrowSyntax)
    kapt(Libraries.arrowMeta)
    // testing dependencies - Unit Test
    testImplementation(Libraries.junit)
    testImplementation(Libraries.mockitoKotlin)
    testImplementation(Libraries.kotlinCoroutinesTest)
    // koin testing tools
    testImplementation(Libraries.koinTest)
}