package tests.dsl
import org.junit.Test
import org.junit.Assert._

/**
 * ���[�N�X�y�[�X����e�G���e�B�e�B���擾����C�ȂǊ�{����Ɋւ���e�X�g
 */
class BasicTest extends BaseTest{
	@Test
	def Workspace���擾�ł���(): Unit = {
		assertNotNull($)
	}
	
	@Test
	def RSPackage���擾�ł���(): Unit = {
		var pkg = project.pkg("test.dsl")
		assertNotNull(pkg)
	}

}