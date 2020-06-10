package com.formation.basics.suites;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({"com.formation.junit5.examples.packageA"
				,"com.formation.junit5.examples.packageB"})
@IncludeClassNamePatterns({"^.*ATests?$"})
@IncludeTags("production")
public class JUnit5TestSuiteExample {

}
