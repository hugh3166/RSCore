package rscore.tests.interpreter
import org.junit.Test
import org.junit.Assert._
import rscore.interpreter.RSInterpreter
import rscore.util.FileUtil
import rscore.interpreter.ScriptHelper

class ScriptHelperTest{
	@Test
	def �p�b�P�[�W�錾�𐳂����o�͂ł���(): Unit = {
		val actual = ScriptHelper.buildImportDeclaration()
		val expected = """
					java_import 'rscore.dsl.entity.RSWorkspace'
					java_import 'rscore.dsl.entity.collection.By'
					java_import 'rscore.dsl.entity.collection.With'
					java_import 'rscore.dsl.entity.collection.WithOr'
					java_import 'rscore.dsl.entity.collection.WithAnd'
					"""
			assertLineBasedEquals(expected, actual)
	}
	
	@Test
	def �u��������΂Ɋ�Đ������u�������ł���(): Unit = {
		val original = "$.projects.first"
		val expected = "RSWorkspace.projects.first"
			
		val actual = ScriptHelper.prepareScript(original)
		assertEquals(expected, actual)
	}
	/**
	 * 
	 */
	private def assertLineBasedEquals(expected: String, actual: String): Unit = {
		val e = FileUtil.eliminateBlankLines(expected, true)
		val a = FileUtil.eliminateBlankLines(actual, true)
		assertEquals(e, a)
	}
}