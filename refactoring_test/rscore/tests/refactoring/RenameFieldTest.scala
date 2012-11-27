package rscore.tests.refactoring
import org.junit.Before
import org.junit.Test
import rscore.dsl.entity.RSWorkspace
import rscore.dsl.util.ImplicitConversions._
import rscore.dsl.entity.collection.By
import org.junit.After
import org.junit.Ignore

class RenameFieldTest extends RefactoringBaseTest {
	override val testGroupIdentifier = "rename_field"

	@Ignore
	def �ϐ���1�������l�[������(): Unit = {
		prepareTest("RenameOneField")
	}
	
	
	/**
	 * �v���t�B�N�X new �ƁC���̖��O��啶���ɂ����������������āC�V�������O�ɕύX����
	 * e.g. private int field -> private int newFIELD
	 */
	@Test
	def private�ȃt�B�[���h�̖��O��ύX����: Unit = {
		val testName = "RenamePrivateFields"
		prepareTest(testName)

		val $ = RSWorkspace
		$.project(projectName).pkg(testGroupIdentifier).classes.select(By.Name(testName)).first
			.fields.select(By.Modifier("private")).elements
			.foreach(field => field.rename("new" + field.name.toUpperCase()))

		doAssert(testName)
	}
	
	/*
	@After
	override def tearDown(): Unit = {
		println("teardown... ")
	}
	*/
	
}
