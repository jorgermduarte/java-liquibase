package pt.jorgeduarte.liquibase.app.mappers;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueCheckStrategy;

@MapperConfig(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.generated", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public class GlobalMapperConfig {
}
