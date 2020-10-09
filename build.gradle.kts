import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.*

plugins {
    application
    java
    kotlin("jvm") version "1.4.10"
    id("idea")
    id("de.fuerstenau.buildconfig") version "1.1.8"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("nu.studer.jooq") version "4.1"
    id("java-library")
}

val ktorVersion: String by project
val kotlinVersion: String by project
val kotlinxCoroutinesVersion: String by project
val logbackVersion: String by project

/*********************************************************************/
/**                 Application Basic Information                   **/
/*********************************************************************/

group = "com.kenvix"
version = "1.0"
val applicationName = "StudentManagerJ"
val versionCode = 1

val archivesBaseName = "studentmanager"
val mainSrcDir = "src/main"
val testSrcDir = "src/test"
val generatedSrcDir = "generatedSrc"
val fullPackageName = "${group}.$archivesBaseName"
val fullPackagePath = fullPackageName.replace('.', '/')
val isReleaseBuild = System.getProperty("isReleaseBuild") != null
val systemProperties: Properties = System.getProperties()
val libDirName = "libs"
val mainClass = "${fullPackageName}.Main"
systemProperties.setProperty("project.jooqVersion", "3.13.1")
systemProperties.setProperty("project.jooqPropFile", "jooq.properties")

/*********************************************************************/
/**                 Application Dependencies                        **/
/*********************************************************************/

configure<JavaPluginConvention> {
    this.sourceCompatibility = org.gradle.api.JavaVersion.VERSION_15
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree("libs"))

    testImplementation(kotlin("test-junit5"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.6")

    implementation("com.squareup.okhttp3:okhttp:3.5.0")
    implementation("com.google.code.gson:gson:2.8.5")

    // https://mvnrepository.com/artifact/javax.persistence/javax.persistence-api
    implementation(group = "javax.persistence", name = "javax.persistence-api", version = "2.2")
    //添加jooq依赖
    implementation(group = "org.jooq", name = "jooq", version = "3.11.5")
    //<!> 一定要添加所用数据库的依赖，否则会报错而且不告诉你原因
    implementation(group = "org.xerial", name = "sqlite-jdbc", version = "3.25.2")
    implementation(group = "mysql", name = "mysql-connector-java", version = "8.0.17")
    implementation(group = "org.apache.commons", name = "commons-dbcp2", version = "2.7.0")

    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("io.github.cdimascio:java-dotenv:5.1.4")
    implementation("io.javalin:javalin:3.11.0")
    implementation("org.freemarker:freemarker:2.3.30")
    testImplementation(kotlin("script-runtime"))
}

// Add generated build-config directories to the main source set, so that the
// IDE doesn"t complain when the app references BuildConfig classes
kotlin.sourceSets["main"].kotlin.srcDirs(mainSrcDir)
kotlin.sourceSets["test"].kotlin.srcDirs(testSrcDir)

sourceSets {
    getByName("main").apply {
        java.srcDirs(
            File(mainSrcDir),
            File(buildDir, "gen/buildconfig/src"),
            File(buildDir, "src")
        )
        resources.srcDirs("$mainSrcDir/resources")
    }

    getByName("test").apply {
        java.srcDirs(
            File(testSrcDir),
            File(buildDir, "gen/buildconfig/src"),
            File(buildDir, "src")
        )
        resources.srcDirs("$testSrcDir/resources")
    }
}


/*********************************************************************/
/**                 Application Build Config Settings               **/
/*********************************************************************/

buildConfig {
    appName = project.name       // sets value of NAME field
    version = project.version.toString()   // sets value of VERSION field,
    // "unspecified" if project.version is not set

    clsName = "BuildConfig"      // sets the name of the BuildConfig class
    packageName = fullPackageName  // sets the package of the BuildConfig class,
    // "de.fuerstenau.buildconfig" if project.group is not set
    charset = "UTF-8"            // sets charset of the generated class,
    // "UTF-8" if not set otherwise

    buildConfigField("String", "APPLICATION_NAME", applicationName)
    buildConfigField("String", "VERSION_NAME",     version)
    buildConfigField("String", "PACKAGE_NAME",     fullPackageName)
    buildConfigField("int",    "VERSION_CODE",     versionCode.toString())
    buildConfigField("long",   "BUILD_UNIXTIME",   System.currentTimeMillis().toString() + "L")
    buildConfigField("java.util.Date", "BUILD_DATE", "new java.util.Date(" + System.currentTimeMillis() + "L)")
    buildConfigField("String", "BUILD_USER",       System.getProperty("user.name"))
    buildConfigField("String", "BUILD_JDK",        System.getProperty("java.version"))
    buildConfigField("String", "BUILD_OS",         System.getProperty("os.name"))
    buildConfigField("String", "LIBRARY_DIR_NAME", libDirName)
    buildConfigField("String", "MAIN_CLASS_NAME",  mainClass)
    buildConfigField("boolean","IS_RELEASE_BUILD", isReleaseBuild.toString())

    val jooqFile = project.file(System.getProperty("project.jooqPropFile"))
    if (jooqFile.exists()) {
        val props = Properties()
        props.load(jooqFile.inputStream())
        buildConfigField("String", "DATABASE_NAME", props.getProperty("jooq.srcDatabaseName"))
    }
}

/*********************************************************************/
/**                            Tasks                                **/
/*********************************************************************/

systemProperties.setProperty("project.fullPackageName", fullPackageName)
systemProperties.setProperty("project.fullPackagePath", fullPackagePath)
systemProperties.setProperty("project.applicationName", applicationName)
systemProperties.setProperty("project.versionCode", versionCode.toString())
systemProperties.setProperty("project.archivesBaseName", archivesBaseName)
systemProperties.setProperty("project.mainSrcDir", mainSrcDir)
systemProperties.setProperty("project.testSrcDir", testSrcDir)
systemProperties.setProperty("project.generatedSrcDir", generatedSrcDir)
systemProperties.setProperty("project.mainClassName", mainClass)

apply(from = "enableJooq.gradle")

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
        kotlinOptions.freeCompilerArgs += "-Xjvm-default=enable"
    }

    withType<JavaCompile> {
        options.encoding = "utf-8"

        if (systemProperties.getProperty("project.generateORM") != null) {
            dependsOn("generateORM")
        } else {
            logger.info("Will not update jOOQ ORM at build time, please manually run generateORM gradle task or add -Dproject.generateORM=true to gradle JVM options")
        }
    }

    register("copyJarLibs", Copy::class.java) {
        doLast {
            into("${buildDir}/output/$libDirName")
            from(configurations.runtime)
        }
    }
}