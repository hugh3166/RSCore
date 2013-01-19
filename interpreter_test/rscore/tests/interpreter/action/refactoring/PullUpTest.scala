package rscore.tests.interpreter.action.refactoring
import rscore.tests.interpreter.InterpreterRefactoringBaseTest
import org.junit.Test

class PullUpTest extends InterpreterRefactoringBaseTest{
	override val testGroupIdentifier = "pullup"
	
	@Test
	def �����̃t�B�[���h�𓯈�t�@�C�����̃X�[�p�[�N���X�Ɉ����グ��(): Unit = {
		val testName = "PullUpTest0"
		prepareTest(testName)

		val script = """
		sub_class = project.pkg("%s").classes().Select(By.Name("SubClass")).first
		field = sub_class.fields.first

		super_class = sub_class.superclass
		field.pull_up(super_class)
		""".format(this.testGroupIdentifier)
		
		interpreter.execScript(script)

		doAssert(testName)
	}
	

}