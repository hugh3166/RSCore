package rscore.tests.refactoring
import org.junit.Before
import org.junit.After
import org.eclipse.jdt.core.IPackageFragmentRoot
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.core.resources.IWorkspaceRunnable
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.ltk.core.refactoring.RefactoringCore
import org.junit.Test
import org.junit.Assert._
import org.eclipse.core.runtime.NullProgressMonitor
import java.io.FileNotFoundException
import org.junit.Assert
import rscore.util.FileUtil
import rscore.tests.common.BaseTest
import rscore.tests.common.TestHelper
import rscore.tests.common.ActionBaseTest

class RefactoringBaseTest extends ActionBaseTest{
	protected def doAssert(testName: String, ignoreComment: Boolean = false): Unit = {
		val actualSource = FileUtil.eliminateBlankLines(
			this.fgPackageP.getCompilationUnit(testName + ".java").getSource())
		val outputFilepath = "test_resources_output/" + testGroupIdentifier + "/" + testName + ".java"
		val expectedSource = FileUtil.getFileContents(outputFilepath)
		
		doAssertHelper(expectedSource, actualSource, ignoreComment)
	}
	// alias to above method
	protected def doAssertTrue(testName: String, ignoreComment: Boolean = false): Unit = {
		this.doAssert(testName, ignoreComment)
	}
	

}