package me.bjxg.demo.mock.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

/**
 * mock静态方法示例
 * Created by chengcy on 2017/3/9.
 */
@Slf4j
@PrepareForTest({Validator.class})//要使用PowerMock高级功能时，比如：Mock静态、final、私有方法，构造方法等时需此注解
public class ValidatorTest {
    @Rule //代替用powermock RunWith
    public PowerMockRule powerMockRule = new PowerMockRule();

    @Test
    public void testIsBlank() throws Exception {
        // 要mock本类部分方法时用spy，全部方法用mockStatic
        PowerMockito.spy(Validator.class);
        // 开始mock isEmpty方法，不管输入任何参数，总是返回false
        PowerMockito.when(Validator.isEmpty(Mockito.anyString())).thenReturn(false);

        Assert.assertTrue(Validator.isBlank("  "));
        log.info("全空格字符串测试通过");

        Assert.assertFalse(Validator.isBlank(" no blank "));
        log.info("非空字符串测试通过");
    }

}