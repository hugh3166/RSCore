package tests.dsl
import org.junit.Test
import org.junit.Assert._
import org.junit.Before
import dsl.entity.RSWorkspace

/**
 * ���[�N�X�y�[�X����e�G���e�B�e�B���擾����C�ȂǊ�{����Ɋւ���e�X�g
 */
class BasicTest extends DSLBaseTest{
	@Before
	override def setUp(): Unit = {
		super.setUp()
		prepareTest("BasicTest")
	}
		
	@Test
	def Workspace���擾�ł���(): Unit = {
		assertNotNull($)
	}
	
	@Test
	def RSPackage���擾�ł���(): Unit = {
		var pkg = $.project(this.projectName).pkg("find_test")
		assertNotNull(pkg)
	}

}