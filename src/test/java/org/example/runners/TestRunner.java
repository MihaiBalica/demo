package org.example.runners;


import org.example.tests.HttpMethodsTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({HttpMethodsTest.class})
public class TestRunner {
}