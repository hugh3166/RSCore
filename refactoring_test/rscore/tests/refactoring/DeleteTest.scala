package rscore.tests.refactoring
import rscore.tests.common.ActionBaseTest
import org.junit.Test

class DeleteTest extends RefactoringBaseTest{
	override val testGroupIdentifier = "delete"
		
	@Test
	def ���\�b�h���폜�ł���(): Unit = {
		val testName = "MethodDelete"
			prepareTest(testName)
		val m = $.project(this.projectName).pkg(testGroupIdentifier).classes.first.methods.first
		println(m)
		m.delete()
		
		doAssert(testName)
	}

}