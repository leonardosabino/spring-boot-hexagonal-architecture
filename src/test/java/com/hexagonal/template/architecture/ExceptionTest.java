package com.hexagonal.template.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.hexagonal.template")
public class ExceptionTest {

    @ArchTest
    static ArchRule shouldNotThrowGenericExceptions = noClasses().should(GeneralCodingRules.THROW_GENERIC_EXCEPTIONS)
            .andShould().resideOutsideOfPackages("..transportLayer.openapi.api..");

}
