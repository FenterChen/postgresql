package com.wanin.rd.postgresql_server.config


import org.flywaydb.core.Flyway
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
@ConfigurationProperties(prefix ="spring.datasource")
data class PostgresqlDatasource(
    var url: String?,
    var username: String?,
    var password: String?,
){
    val schemas:String="daniel"
}


@Configuration
class FlywayConfig {

    @Bean
    fun flyway(dataSource: DataSource?,postgresqlDatasource:PostgresqlDatasource): Flyway? {
        val flyway = Flyway.configure()
            .dataSource(postgresqlDatasource.url, postgresqlDatasource.username, postgresqlDatasource.password)
            .schemas(postgresqlDatasource.schemas).baselineOnMigrate(true).load()
        flyway.migrate()
        return flyway
    }

}
