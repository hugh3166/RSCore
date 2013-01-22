package rscore.tests.application
import org.junit.Test

class PullUpMultipleMembersTest extends ApplicationTest{
	@Test
	def ���ׂĂ̂̃����o����C��PullUp����(): Unit = {
		println(this.getClass().toString())
		val testName = "PullUpMultipleMembersTest"
		prepareTest(testName)
		
		val members = getFirstClass().members()
		val superClass = getFirstClass().superclass()
		
		members.pullUp(superClass)
		
		doAssert(testName)
	}

}