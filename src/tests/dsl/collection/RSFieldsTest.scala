package tests.dsl.collection
import org.eclipse.ui.IWorkbench
import org.junit.Test
import org.junit.Assert._
import dsl.entity.RSWorkspace
import org.junit.Before
import dsl.entity.RSWorkspace
import dsl.entity.RSProject
import scala.reflect.This
import dsl.entity.collection.By
import dsl.util.ImplicitConversions._
import dsl.entity.collection.With
import dsl.entity.collection.WithAnd
import tests.dsl.BaseTest
import dsl.entity.RSClass

class FieldsTest extends BaseTest{
	var cls: RSClass = null
	
	@Before
	override def setUp(): Unit ={
		super.setUp()
		this.cls = project.pkg("test.dsl").classes.first
	}
	
	@Test
	def ���O����t�B�[���h���i�荞�߂�(): Unit = { 
		// var cls = project.pkg("test.dsl").classes.first
		var publicIntField = cls.fields.select(By name With("publicInt"))
		assertEquals(1, publicIntField.length)
	}
	
	@Test
	def �C���q����t�B�[���h���i�荞�߂�(): Unit = {
		var publicFields = this.cls.fields.select(By.modifier(With("public")))
		// assertEquals(1, publicFields.length)
		
		var privateFields = this.cls.fields.select(By.modifier(With("private")))
		assertEquals(1, privateFields.length)
		
		var protectedFields = this.cls.fields.select(By.modifier(With("protected")))
		assertEquals(1, protectedFields.length)
		
		var publicStaticFields = this.cls.fields.select(By.modifier(WithAnd("public", "static")))
		assertEquals(0, publicStaticFields.length)
	}
	
	@Test
	def �^������t�B�[���h���i�荞�߂�(): Unit = {
		
	}
	
}