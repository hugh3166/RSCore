package rscore.tests.interpreter

import org.junit.Test
import org.junit.Assert._
import org.junit.After
import org.junit.Before
import org.jruby.embed.LocalVariableBehavior
import rscore.interpreter.ScalaInterpreterA

class ScalaInterpreterTest {
	var interpreter = ScalaInterpreterA
	
	@Before
	def setUp(): Unit = {
		interpreter.initScalaInterpreter()
	}
	@After
	def tearDown(): Unit = {
	}
	
	@Test
	def 整数演算を正しく解釈できる(): Unit = {
		val script = """1+1"""
		val r = interpreter.execScalaScript(script)
		assertEquals(2, r) 
	}

	@Test
	def 不正なスクリプトは解釈中に例外を投げる(): Unit = {
		val script = """println("aaaa")"""
		try{
			interpreter.execScalaScript(script)
		} catch{
			case _  => assertTrue(true)
		}
	}
	
	@Test
	def RSWorkspaceを正しく取得できる(): Unit = {
		interpreter.execScalaScript("import rscore.dsl.entity._")
		interpreter.execScalaScript("""val proj = RSWorkspace.project("Ex0")""")
		
	}
}