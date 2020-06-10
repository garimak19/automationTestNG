package automationTestNG;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class TC_login {

	@Test(groups="Regression")
	public void Login_01() {
		System.out.println("login 1");
	}
	@Test(groups="Sanity")
	public void Login_02() {
		System.out.println("login 2");
	}
	@Test
	public void Login_03() {
		System.out.println("login 3");
	}
	@Test
	public void Login_04() {
		System.out.println("login 4");
	}
}
