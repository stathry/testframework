package com.github.testframework.junit4;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * JunitOrderedRunner
 *
 * @author dongdaiming(董代明)
 * @date 2019-07-22
 */
public class JunitOrderedRunner extends BlockJUnit4ClassRunner {
    /**
     * Constructs a new instance of the default runner
     *
     * @param klass
     */
    public JunitOrderedRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> list = new ArrayList<>(super.computeTestMethods());
        if(list.isEmpty()) {
            return list;
        }
        Collections.sort(list, (method1, method2) -> {
            MethodOrder annotation1 = method1.getAnnotation(MethodOrder.class);
            MethodOrder annotation2 = method2.getAnnotation(MethodOrder.class);
            int order1 = annotation1 == null ? 1024 : annotation1.value();
            int order2 = annotation2 == null ? 1024 : annotation2.value();
            return order1 - order2;
        });
        return list;
    }


}
