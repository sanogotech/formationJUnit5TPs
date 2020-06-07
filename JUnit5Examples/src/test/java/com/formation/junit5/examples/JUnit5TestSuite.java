package com.formation.junit5.examples;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.formation.junit5.examples")
@IncludePackages("com.formation.junit5.examples.packageC")
@ExcludeTags("PROD")
public class JUnit5TestSuite {

}
