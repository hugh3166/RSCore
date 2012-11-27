package rscore.tests.dsl.collection
import org.junit.Before
import org.junit.Test
import org.junit.Assert._
import rscore.dsl.util.ImplicitConversions._
import rscore.dsl.entity.collection.By
import rscore.dsl.entity.collection.With._
import rscore.dsl.entity.collection.WithOr
import rscore.dsl.entity.collection.With
import rscore.tests.dsl.DSLBaseTest
import rscore.dsl.entity.RSProject
import rscore.dsl.entity.RSWorkspace

class RSClassesTest extends DSLBaseTest{
	private var project: RSProject = _
	
	@Before
	override def setUp(): Unit = {
		super.setUp()
		prepareTest("RSClassesTest")
		project = $.project(this.projectName)
	}
	
	@Test
	def RSClass���I�v�V�����ɉ����Đ������擾�ł���(){
		var classesWithInnerClass = project.pkg("find_test").classes(true)
		assertEquals(3, classesWithInnerClass.length)
		
		var classesWithoutInnerClass = project.pkg("find_test").classes(false)
		assertEquals(2, classesWithoutInnerClass.length)
		
		// classes �̃I�v�V�������w�肵�Ȃ���΁C�C���i�[�N���X���܂߂ĕԂ�
		assertEquals(3, project.pkg("find_test").classes.length)
	}
	
	@Test
	def ���O����N���X���i�荞�߂�(): Unit = {
		var c = project.pkg("find_test").classes(true).select(By.Name(With.or("RSClassesTest")))
		assertEquals(1, c.length)
	}
	
	@Test
	def �C���q����N���X���i�荞�߂�(): Unit = {
		val c = project.pkg("find_test").classes(true).select(By.Modifier(With.or("public"))).select(By.Modifier(With.or("public")))
		assertEquals(1, c.length)
		val pc = project.pkg("find_test").classes(true).select(By.Modifier(With.or("public"))).select(By.Modifier(With.or("private")))
		assertEquals(0, pc.length)
	}
		
}
