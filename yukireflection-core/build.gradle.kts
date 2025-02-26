plugins {
    autowire(libs.plugins.kotlin.jvm)
    autowire(libs.plugins.maven.publish)
}

group = property.project.groupName
version = property.project.yukireflection.core.version

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs = listOf(
            "-Xno-param-assertions",
            "-Xno-call-assertions",
            "-Xno-receiver-assertions",
            "-Xcontext-receivers",
        )
    }
}

dependencies {
    compileOnly(autowire("libs/android-34.jar"))
    compileOnly(autowire("libs/KYukiHookAPI_V1.0.0.jar"))// Hook API
    api(org.jetbrains.kotlin.kotlin.reflect)
}

mavenPublishing {
    coordinates(
        groupId = group.toString(),
        artifactId = property.project.yukireflection.core.moduleName,
        version = version.toString()
    )
}