package rscore.tests.application
import org.junit.Test

class PullUpMultipleMembersTest extends ApplicationTest{
	@Test
	def すべてののメンバを一気にPullUpする(): Unit = {
		println(this.getClass().toString())
		val testName = "PullUpMultipleMembersTest"
		prepareTest(testName)
		
		val members = getFirstClass().members()
		val superClass = getFirstClass().superclass()
		
		members.pullUp(superClass)
		
		doAssert(testName)
	}

}