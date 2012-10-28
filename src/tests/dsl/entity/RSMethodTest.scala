package tests.dsl.entity
import org.junit.Test
import tests.dsl.BaseTest
import org.junit.Before
import dsl.entity.RSMethod
import org.junit.Assert._

class MethodTest extends BaseTest {
	var methods: Array[RSMethod] = null

	@Before
	override def setUp(): Unit = {
		super.setUp()
		this.methods = project.pkg("test.dsl").classes.first.methods
		assertEquals(4, this.methods.length)
	}
	@Test
	def ���\�b�h�̖��O�𐳂����擾�ł���(): Unit = {
		var expectedNames = Array[String](
			"publicVoidMethod",
			"privateIntMethod",
			"protectedStringMethod",
			"publicStaticVoidMethod")
		for(i <- 0 until expectedNames.length){
			assertEquals(expectedNames(i), methods(i).name)
		}
	}

	@Test
	def ���\�b�h�̖߂�l�𐳂����擾�ł���(): Unit = {
		
	}

	@Test
	def ���\�b�h�̃V�O�l�`���𐳂����擾�ł���(): Unit = {

	}

}