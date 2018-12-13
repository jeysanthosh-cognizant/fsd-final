package com.fsd.finalproj.projectmanagerapi;

import org.junit.runner.Runner;
import org.junit.runners.parameterized.BlockJUnit4ClassRunnerWithParameters;
import org.junit.runners.parameterized.ParametersRunnerFactory;
import org.junit.runners.parameterized.TestWithParameters;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class SpringParametersRunnerFactory implements ParametersRunnerFactory {
@Override
  public Runner createRunnerForTestWithParameters(TestWithParameters test) throws org.junit.runners.model.InitializationError {
    final BlockJUnit4ClassRunnerWithParameters runnerWithParameters = new BlockJUnit4ClassRunnerWithParameters(test);
    return new SpringJUnit4ClassRunner(test.getTestClass().getJavaClass()) {
      @Override
      protected Object createTest() throws Exception {
        final Object testInstance = runnerWithParameters.createTest();
        getTestContextManager().prepareTestInstance(testInstance);
        return testInstance;
      }
    };
  }
}