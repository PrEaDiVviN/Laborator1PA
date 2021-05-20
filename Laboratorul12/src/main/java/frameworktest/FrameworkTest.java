package frameworktest;

import addnot.Test;
import exampleclass.Person;

import java.lang.reflect.Method;

public class FrameworkTest {
    public static void Testing(Class clasa) throws Exception {
        int passed = 0, failed = 0;
        for (Method m : clasa.getMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                try {
                    m.invoke(null);
                    passed++;
                }
                catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n",m, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }
}
