package com.github.testframework.powermock;

import com.github.testframework.PowerMockCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * PowerMockAndStubTest
 * Created by dongdaiming on 2018-12-29 14:31
 */

@PowerMockIgnore({"javax.management.", "com.sun.org.apache.xerces.",
        "javax.xml.", "org.xml.", "org.w3c.dom.",
        "com.sun.org.apache.xalan.", "javax.activation.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(PowerMockCase.class)
public class PowerMockAndStubTest {

    @Test
    public void testMockitoStubVsPowerMock() {
        List mockedList = Mockito.mock(List.class);
        Mockito.when(mockedList.get(0)).thenReturn("aa");
        Mockito.when(mockedList.get(1)).thenReturn("bb");
        Assert.assertEquals("aa", mockedList.get(0));
        Assert.assertEquals("bb", mockedList.get(1));

        List mockedList2 = PowerMockito.mock(List.class);
        Mockito.when(mockedList2.get(0)).thenReturn("cc");
        Mockito.when(mockedList2.get(1)).thenReturn("dd");
        Assert.assertEquals("cc", mockedList2.get(0));
        Assert.assertEquals("dd", mockedList2.get(1));
    }

    @Test
    public void testStubAndThenReturns() {
        Iterator itr = PowerMockito.mock(Iterator.class);
        Mockito.when(itr.next()).thenReturn("hello1").thenReturn("hello2").thenReturn("hello3");

        Assert.assertEquals("hello1", itr.next());
        Assert.assertEquals("hello2", itr.next());
        Assert.assertEquals("hello3", itr.next());
        Assert.assertEquals("hello3", itr.next());
    }

    @Test
    public void testMockStaticMethod() {
        // mock static方法
        PowerMockito.mockStatic(PowerMockCase.class);
        PowerMockito.when(PowerMockCase.staticMethod1(Mockito.anyInt())).thenReturn(1);
        Assert.assertEquals(1, PowerMockCase.staticMethod1(0));
        PowerMockito.verifyStatic(PowerMockCase.class);
    }

    @Test
    public void testMockPrivateMethod() throws Exception {
        PowerMockCase object = PowerMockito.spy(new PowerMockCase());
        PowerMockito.doReturn(3).when(object, "privateMethod1", 2);
        Assert.assertEquals(3, object.toPrivateMethod1(2));
        PowerMockito.verifyPrivate(object).invoke("privateMethod1", 2);
    }

    @Test
    public void testMockFinalMethod() throws Exception {
        PowerMockCase object = PowerMockito.spy(new PowerMockCase());
        PowerMockito.doReturn(4).when(object, "finalMethod1", 3);
        Assert.assertEquals(4, object.finalMethod1(3));
    }

    @Test
    public void testMockNewMethod() throws Exception {
        File mockedFile1 = PowerMockito.mock(File.class);
        PowerMockito.whenNew(File.class).withArguments("path1").thenReturn(mockedFile1);
        PowerMockito.when(mockedFile1.exists()).thenReturn(true);
        Assert.assertEquals(true, mockedFile1.exists());

        File mockedFile2 = PowerMockito.mock(File.class);
        PowerMockito.whenNew(File.class).withArguments("path2").thenReturn(mockedFile2);
        PowerMockito.when(mockedFile2.exists()).thenReturn(false);
        Assert.assertEquals(false, mockedFile2.exists());
    }

}
