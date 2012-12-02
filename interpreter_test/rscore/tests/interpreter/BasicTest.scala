package rscore.tests.interpreter
import org.junit.Test
import org.junit.Assert._
import rscore.interpreter.RSInterpreter
import org.junit.After
import org.junit.Before
import org.jruby.embed.LocalVariableBehavior

class BasicTest{
	var interpreter = RSInterpreter
	
	@Before
	def setUp(): Unit = {
		interpreter.initContainer(LocalVariableBehavior.PERSISTENT, false)
	}
	@After
	def tearDown(): Unit = {
		interpreter.terminateContainer()
	}
	
	@Test
	def �������Z�𐳂������߂ł���(): Unit = {
		val script = """1+1"""
		val r = interpreter.execScript[Long](script)
		assertEquals(2, r) 
	}
	
	@Test
	def �����񉉎Z�𐳂������߂ł���(): Unit = {
		val script = """"hello " + "world""""
		val r = interpreter.execScript[String](script)
		assertEquals("hello world", r) 
	}
	
	@Test
	def �ϐ��̃Z�b�g�Ǝ��o�����ł���(): Unit = {
		val variableName = "variable"
		val expected = 123
		interpreter.assignVariable(variableName, expected)
		
		// NOTE: arithmetic number cannnot be casted to Int
		val actual = interpreter.getVariable[Long](variableName).get
		
		assertEquals(expected, actual)
	}
	
	@Test
	def �s���ȃX�N���v�g�͉��ߒ��ɗ�O�𓊂���(): Unit = {
		val script = """pputs "foo" """
		try{
			interpreter.execScript(script)
			fail()
		} catch{
			case _  => assertTrue(true)
		}
	}
}