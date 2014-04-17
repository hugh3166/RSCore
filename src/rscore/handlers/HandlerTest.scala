package rscore.handlers

import org.eclipse.core.commands.AbstractHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchWindow
import org.eclipse.ui.handlers.HandlerUtil
import org.eclipse.core.runtime.NullProgressMonitor;

import ch.ethz.resynth.eclipse.EclipseRefactoringSequenceExecutor
import ch.ethz.resynth.tree.Diff
import ch.ethz.resynth.tree.ProjectTree
import ch.ethz.resynth.tree.Refactor
import ch.ethz.resynth.tree.RefactorSearch
import ch.ethz.resynth.tree.RefactorSearchAStar
import ch.ethz.resynth.tree.ProjectTreeBuilder

import rscore.dsl.entity.RSWorkspace
import rscore.dsl.entity.collection._

import scala.util.control.Breaks.{break, breakable}

class HandlerTest extends AbstractHandler {
    
    /**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	def execute(event: ExecutionEvent): Object = {
		val proj = RSWorkspace.project("Experiment")
		val cls = proj.pkg("p").classes.select(By.Name("MyGameP")).first
		
		val md1 = cls.method("test1").first
		val md2 = cls.method("test2").first
		val tree1: ProjectTree = new ProjectTree
		val tree2: ProjectTree = new ProjectTree
		tree1.setName("<METHODBODY>")
		tree2.setName("<METHODBODY>")
		ProjectTreeBuilder.generateProjectTreeFromASTNode(tree1, md1.getDeclaration)
		ProjectTreeBuilder.generateProjectTreeFromASTNode(tree2, md2.getDeclaration)
		
		val diff: Diff = new Diff(tree1, tree2);
		val t1: ProjectTree = diff.treeWithNoEqualKeys(true);
		val t2: ProjectTree = diff.treeWithNoEqualKeys(false);
		t1.freeze();
		t2.freeze();
		
		val s: RefactorSearchAStar = new RefactorSearchAStar(t1, t2);
		
		try{
			s.restartSearch();
			var success = false;
//			var time = System.currentTimeMillis()
			var numGlobalFailures = 0;
			
			breakable {
			    for (i <- 0 until 3) {
					val refactorSteps = s.findPath();
					if (refactorSteps == null)
						break;
			
					val seq = new EclipseRefactoringSequenceExecutor(new NullProgressMonitor());
					val window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
					val successfulFullSequence = seq.execute(window, refactorSteps);
					
					if (successfulFullSequence) {
						return null
					}
					numGlobalFailures += 1;
					//"Failed to run sequence "
				}
			}

		}catch{
		    case e: CoreException =>
				e.printStackTrace();
		}
	    
	    
//	    val handler = new ReSynthRefactor()
//	    handler.execute(event)
	    
		return null
	}

}
