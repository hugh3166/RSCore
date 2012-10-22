package tests.dsl
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class RSClassTest extends BaseTest{
	@Before
	override def setUp(): Unit = {
		this.project = $.project("Sample")
	}
	
	@Test
	def RSClass�𐳂����擾�ł���(){
		var classesWithInnerClass = project.pkg("rename").classes(true)
		assertEquals(3, classesWithInnerClass.length)
		
		var classesWithoutInnerClass = project.pkg("rename").classes(false)
		assertEquals(2, classesWithoutInnerClass.length)
	}

}