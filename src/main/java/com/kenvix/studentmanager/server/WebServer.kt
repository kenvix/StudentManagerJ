package com.kenvix.studentmanager.server

import com.kenvix.studentmanager.BuildConfig
import com.kenvix.utils.preferences.ServerEnv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.dbcp2.BasicDataSource
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.conf.MappedSchema
import org.jooq.conf.RenderMapping
import org.jooq.conf.Settings
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConfiguration
import org.slf4j.LoggerFactory
import java.io.File


object WebServer {
    internal val logger = LoggerFactory.getLogger(javaClass)

    /**
     * Create a new jooq DSL context
     * Note that you do NOT need to keep a reference to a DSLContext.
     * This field must be called after MySQL Database initialization.
     */
    val dslContext: DSLContext
        get() = DSL.using(currentThreadJooqConfiguration)

    var currentThreadJooqConfiguration: Configuration
        get() = currentThreadJooqConfigurationOrNull ?: jooqConfiguration
        set(value) { currentThreadJooqConfigurationOrNull = value }

    var currentThreadJooqConfigurationOrNull: Configuration?
        get() = currentThreadJooqConfigurationContainer.get()
        set(value) {
            if (value == null)
                currentThreadJooqConfigurationContainer.remove()
            else
                currentThreadJooqConfigurationContainer.set(value)
        }

    private val currentThreadJooqConfigurationContainer = InheritableThreadLocal<Configuration>()

    val jooqConfiguration: Configuration
        get() = realJooqConfiguration.derive()

    @JvmStatic
    private lateinit var realJooqConfiguration: Configuration

    lateinit var dataSource: BasicDataSource
        private set

    init {
        if(System.getProperty("hotReloadSupported")?.toBoolean() == true)
            println("CREATED WEB SERVER INTANCE " + hashCode() + " / LOADER " + javaClass.classLoader)
    }

    suspend fun start() = withContext(Dispatchers.IO) {
        logger.info("Server preloading")
        preload()

        logger.info("Starting database pool")
        val mysql = launch { startMySQLDatabasePool() }
        logger.debug("Waiting database online ...")
        mysql.join()
    }

    internal suspend fun preload() {
        checkFiles()
    }

    internal suspend fun startMySQLDatabasePool() = withContext(Dispatchers.IO) {
        if (!this@WebServer::realJooqConfiguration.isInitialized) {
            logger.info("Loading MySQL Database: ${ServerEnv.DatabaseUser}@${ServerEnv.DatabaseHost}" +
                    ":${ServerEnv.DatabasePort}/${ServerEnv.DatabaseName}")

            System.getProperties().setProperty("org.jooq.no-logo", "true")
            Class.forName("com.mysql.cj.jdbc.Driver")
            dataSource = BasicDataSource()

            val param = "useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&autoReconnectForPools=true" +
                    "&serverTimezone=GMT%2B8"
            val url = "jdbc:mysql://${ServerEnv.DatabaseHost}:${ServerEnv.DatabasePort}" +
                    "/${ServerEnv.DatabaseName}?$param"

            //dataSource.driverClassName = "mysql.mysql-connector-java"
            dataSource.url = url
            dataSource.username = ServerEnv.DatabaseUser

            if (ServerEnv.DatabasePassword.isNotBlank())
                dataSource.password = ServerEnv.DatabasePassword

            val mapping = MappedSchema()
                .withInput(BuildConfig.DATABASE_NAME)
                .withOutput(ServerEnv.DatabaseName)

            val settings = Settings()
                .withRenderMapping(RenderMapping().withSchemata(mapping))
                .withRenderSchema(false)

            realJooqConfiguration = DefaultConfiguration().set(settings).set(dataSource).set(SQLDialect.MYSQL)

            val version = dslContext.resultQuery("SHOW VARIABLES LIKE \"version\";")
                    .also { it.execute() }.result[0].valuesRow()
            logger.info("Mysql Database connection test pass! $version")
        }
    }

    private suspend fun checkFiles() = withContext(Dispatchers.IO) {
        val publicDir = File("public")

        if (!publicDir.exists())
            publicDir.mkdirs()
    }

//    fun registerRoutes(application: Application, testing: Boolean = false) {
//        WebServerBasicRoutes.module(application, testing)
//
//        val reflections = Reflections(BuildConfig.PACKAGE_NAME + ".http.routes", SubTypesScanner(true))
//        val modules = reflections.getSubTypesOf(KtorModule::class.java)
//        for (module in modules) {
//            val instance = module.getConstructor().newInstance()
//            if (instance is KtorModule) {
//                instance.module(application, testing)
//            } else {
//                logger.warn("Invalid route class: ${module.name}")
//            }
//        }
//    }
}