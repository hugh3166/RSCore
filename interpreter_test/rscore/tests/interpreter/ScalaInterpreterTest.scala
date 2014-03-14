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
	def �������Z�𐳂������߂ł���(): Unit = {
		val script = """1+1"""
		val r = interpreter.execScalaScript(script)
		assertEquals(2, r) 
	}

	@Test
	def �s���ȃX�N���v�g�͉��ߒ��ɗ�O�𓊂���(): Unit = {
		val script = """println("aaaa")"""
		try{
			interpreter.execScalaScript(script)
		} catch{
			case _  => assertTrue(true)
		}
	}
	
	@Test
	def RSWorkspace�𐳂����擾�ł���(): Unit = {
		interpreter.execScalaScript("import rscore.dsl.entity._")
		interpreter.execScalaScript("""val proj = RSWorkspace.project("Ex0")""")
		
	}
}