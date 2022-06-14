package com.wanin.rd.postgresql_server.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
data class SwaggerContact(
    var name: String?,
    var url: String?,
    var email: String?,
)

@Configuration
@ConfigurationProperties(prefix = "swagger.api-info")
data class SwaggerApiInfo(
    var title: String?,
    var description: String?,
    var contact: SwaggerContact,
    var version: String?,
)

@Configuration
@EnableSwagger2
class SwaggerConfig(private val swaggerApiInfo: SwaggerApiInfo) {
    @Bean
    fun createRestApi(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.wanin.rd.postgresql_server.controller"))
            .paths(PathSelectors.any())
            .build()
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder()
            .title(swaggerApiInfo.title)
            .description(swaggerApiInfo.description)
            .contact(Contact(swaggerApiInfo.contact.name, swaggerApiInfo.contact.url, swaggerApiInfo.contact.email))
            .version(swaggerApiInfo.version)
            .build()
    }
}