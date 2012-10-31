package tests.refactoring
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

class RefactoringBaseTest {
	var fgRoot: IPackageFragmentRoot = _
	var fgPackageP: IPackageFragment = _
	var fgJavaTestProject: IJavaProject = _
	// var fgJRELibrary: IPackageFragmentRoot = _

	@Before
	def setUp(): Unit = {
		println("RefactoringBaseTest :: setUp()")
		this.fgJavaTestProject = RefactoringTestHelper.createJavaProject("TestProject" + System.currentTimeMillis(), "bin")
		this.fgRoot = RefactoringTestHelper.addSourceContainer(this.fgJavaTestProject, "src")
		this.fgPackageP = fgRoot.createPackageFragment("p", true, null)
		this.fgJavaTestProject.open(new NullProgressMonitor)
		
		RefactoringCore.getUndoManager().flush()
	}
	

	@After
	def tearDown(): Unit = {
		println("RefactoringBaseTest :: tearDown()")
		RefactoringTestHelper.delete(this.fgJavaTestProject.getProject())
	}
	

}