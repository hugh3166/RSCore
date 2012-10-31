package tests.dsl.collection
import org.junit.Before
import org.junit.Test
import org.junit.Assert._
import tests.dsl.BaseTest
import dsl.util.ImplicitConversions._
import dsl.entity.collection.By
import dsl.entity.collection.With._
import dsl.entity.collection.WithOr
import dsl.entity.collection.With

class RSClassesTest extends BaseTest{
	@Test
	def RSClass���I�v�V�����ɉ����Đ������擾�ł���(){
		var classesWithInnerClass = project.pkg("test.dsl").classes(true)
		assertEquals(3, classesWithInnerClass.length)
		
		var classesWithoutInnerClass = project.pkg("test.dsl").classes(false)
		assertEquals(2, classesWithoutInnerClass.length)
		
		// classes �̃I�v�V�������w�肵�Ȃ���΁C�C���i�[�N���X���܂߂ĕԂ�
		assertEquals(3, project.pkg("test.dsl").classes.length)
	}
	
	@Test
	def ���O����N���X���i�荞�߂�(): Unit = {
		var c = project.pkg("test.dsl").classes(true).select(By.Name(With.or("FindTest1")))
		assertEquals(1, c.length)
	}
	
	@Test
	def �C���q����N���X���i�荞�߂�(): Unit = {
		var c = project.pkg("test.dsl").classes(true).select(By.Modifier(With.or("public")))
		assertEquals(2, c.length)
	}
		
}