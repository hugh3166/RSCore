package tests.dsl
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class RSClassTest extends BaseTest{
	@Test
	def RSClass�𐳂����擾�ł���(){
		var classesWithInnerClass = project.pkg("test.dsl").classes(true)
		assertEquals(3, classesWithInnerClass.length)
		
		var classesWithoutInnerClass = project.pkg("test.dsl").classes(false)
		assertEquals(2, classesWithoutInnerClass.length)
		
		// classes �̃I�v�V�������w�肵�Ȃ���΁C�C���i�[�N���X���܂߂ĕԂ�
		assertEquals(3, project.pkg("test.dsl").classes.length)
	}

}