package com.example.mwoyeottheora.infrastructure.configuration.property

import com.example.mwoyeottheora.infrastructure.BASE_PACKAGE
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@ConfigurationPropertiesScan(basePackages = [BASE_PACKAGE])
@Configuration
class ConfigurationPropertyConfig
