package com.github.testframework.junit4;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.MethodRule;
import org.junit.rules.RuleChain;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * JunitRuleTest
 *
 * @author dongdaiming(董代明)
 * @date 2019-07-22
 * @see <a href="https://blog.csdn.net/kingmax54212008/article/details/89003076">Junit Rule的使用</a>
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitRuleTest {

    // TemporaryFolder可用于在测试时生成文件并在测试完后自动删除
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder(new File("/temp/junit"));

    // TestName可以获取到当前测试方法的名称
    @Rule
    public TestName testName = new TestName();

    // Timeout可以设置全局的超时时间
    @Rule
    public Timeout timeout = Timeout.seconds(15);

    // 自定义方法规则-打印测试方法名与耗时
    @Rule
    public MethodLoggingRule methodLogger = new MethodLoggingRule();

    // ExpectedException可以匹配异常类型和异常message
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public RuleChain ruleChain = RuleChain.outerRule(new LoggingRule(3)).around(new LoggingRule(2)).around(new LoggingRule(1));

    @Test
    public void testTemporaryFolder() throws Exception {
        File file = temporaryFolder.newFile();
        log.info("file: {}", file.getAbsolutePath());
        FileUtils.writeStringToFile(file, "abc", "utf-8", true);
        log.info("read data: {}", FileUtils.readFileToString(file, "utf-8"));
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void testTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(20);
    }

    @Test
    public void testNotTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void testMethodLogRule() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1234);
    }

    @Test
    public void testExpectException1() {
        expectedException.expect(NumberFormatException.class);
        expectedException.expectMessage("For input string: \"a\"");
        Integer.parseInt("a");
    }

    @Test(expected = ArithmeticException.class)
    public void testExpectException2() {
        int n = 1 / 0;
    }

    @Test
    public void testRuleChain() {
    }

    @Slf4j
    private static class MethodLoggingRule implements MethodRule {

        @Override
        public Statement apply(Statement base, FrameworkMethod method, Object target) {
            String flag = target.getClass().getSimpleName() + "." + method.getName();
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    Stopwatch watch = Stopwatch.createStarted();
                    base.evaluate();
                    log.info("finished {}, duration: {} ms.", flag, watch.elapsed(TimeUnit.MILLISECONDS));
                }
            };
        }
    }

    @Slf4j
    private static class LoggingRule implements TestRule {

        private int priority;

        public LoggingRule(int priority) {
            this.priority = priority;
        }

        @Override
        public Statement apply(Statement base, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    log.info("starting LoggingRule-{}.", priority);
                    base.evaluate();
                    log.info("finished LoggingRule-{}", priority);
                }
            };
        }
    }
}
