package com.github.testframework.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;

/**
 * WhiteBoxTest
 * Created by dongdaiming on 2018-12-29 17:07
 */
@RunWith(PowerMockRunner.class)
public class WhiteBoxTest {

    //Whitebox可以读取和设置private、static、final域的值(查看私有变量时可以不用打断点debug了)
    @Test
    public void testReadFieldByWhiteBox() {
        ArrayList list = new ArrayList();
        Assert.assertEquals(10, ((Integer) Whitebox.getInternalState(ArrayList.class, "DEFAULT_CAPACITY")).intValue());
        Assert.assertEquals(0, ((Integer) Whitebox.getInternalState(list, "modCount")).intValue());
        list.add('a');
        Assert.assertEquals(1, ((Integer) Whitebox.getInternalState(list, "modCount")).intValue());
    }

    @Test
    public void testSetFieldByWhiteBox() {
        ArrayList list = new ArrayList();
        Whitebox.setInternalState(list, "modCount", 8);
        Assert.assertEquals(8, ((Integer) Whitebox.getInternalState(list, "modCount")).intValue());

        Object[] objects = new Object[]{"emma"};
        Whitebox.setInternalState(ArrayList.class, "EMPTY_ELEMENTDATA", objects);
        Assert.assertArrayEquals(objects, Whitebox.getInternalState(ArrayList.class, "EMPTY_ELEMENTDATA"));
    }

    @Test
    public void testInvokePrivateMethod() throws Exception {
        Assert.assertEquals(2147483639, (int)Whitebox.invokeMethod(ArrayList.class, "hugeCapacity", (Integer)100));

        ArrayList list = new ArrayList();
        list.add(2019);
        Assert.assertEquals(2019, (int)Whitebox.invokeMethod(list, "elementData", (Integer)0));
    }
}
