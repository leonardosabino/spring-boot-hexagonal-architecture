package com.hexagonal.template.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

@AnalyzeClasses(packages = "com.hexagonal.template")
public class JodaTimeTest {

    @ArchTest
    static ArchRule shouldNotUseJodaTime = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME;

}
