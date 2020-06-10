package com.formation.basics;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

@Tag("development")
public class ClassATest
{
	@Test
	@Tag("userManagement")
	void testCaseA(TestInfo testInfo) {
	}
}
