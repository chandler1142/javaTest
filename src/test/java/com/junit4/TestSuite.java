package com.junit4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by chandlerzhao on 2017/8/16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestOne.class,
        TestTwo.class
})

public class TestSuite {


}
