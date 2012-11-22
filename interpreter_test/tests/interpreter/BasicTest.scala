package tests.interpreter
import org.junit.Test
import org.junit.Assert._
import interpreter.RSInterpreter
import org.junit.After
import org.junit.Before

class BasicTest{
	var interpreter = RSInterpreter
	
	@Before
	def setUp(): Unit = {
		interpreter.initContainer()
	}
	
	@Test
	def 整数演算を正しく解釈できる(): Unit = {
		val script = """1+1"""
		val r = interpreter.execScript[Long](script)
		assertEquals(2, r) 
	}
	
	@Test
	def 文字列演算を正しく解釈できる(): Unit = {
		val script = """"hello " + "world""""
		val r = interpreter.execScript[String](script)
		assertEquals("hello world", r) 
	}
}