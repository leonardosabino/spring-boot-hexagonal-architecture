package com.hexagonal.template.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "com.hexagonal.template")
public class HexagonalTest {

    @ArchTest
    public static final ArchRule layersValidator = Architectures.layeredArchitecture()
            .layer("Entity").definedBy("..entity..")
            .layer("Interactor").definedBy("..interactor..")
            .layer("Repository").definedBy("..repository..")
            .layer("DataSource").definedBy("..dataSource..")
            .layer("transportLayer").definedBy("..transportLayer..")
            .layer("Config").definedBy("..config..")
            .whereLayer("Interactor").mayOnlyBeAccessedByLayers("transportLayer", "Config")
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Interactor", "DataSource", "Config")
            .whereLayer("DataSource").mayOnlyBeAccessedByLayers("Config")
            .whereLayer("transportLayer").mayOnlyBeAccessedByLayers("Config")
            .whereLayer("Config").mayNotBeAccessedByAnyLayer();

}
