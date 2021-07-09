// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Build.androidGradlePlugin)
        classpath(Build.kotlinGradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        maven("https://dl.bintray.com/arrow-kt/arrow-kt/")
        jcenter() // Warning: this repository is going to shut down soon
    }
}

tasks.register("clean").configure {
    delete("build")
}