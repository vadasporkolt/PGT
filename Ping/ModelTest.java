package Ping;


import static org.junit.Assert.*;

import org.junit.Test;

public class ModelTest {

	@Test
	public final void testTeszttest() {
		Model tester = new Model();
		assertSame(3,tester.Teszttest(1, 2));
	}

	@Test
	public final void testMuvelet() {
		fail("Not yet implemented"); // TODO
	}

}
