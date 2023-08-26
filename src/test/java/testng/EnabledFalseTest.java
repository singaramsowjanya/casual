package testng;

import org.testng.annotations.Test;

public class EnabledFalseTest {
	@Test(invocationCount=0)
public void test1()
{
	System.out.println("test1");
}
@Test(enabled=false)
public void test2()
{
	System.out.println("test2");
}

}
