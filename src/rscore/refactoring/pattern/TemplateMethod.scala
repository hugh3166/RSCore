package rscore.refactoring.pattern

import org.eclipse.ui.handlers.HandlerUtil
import org.eclipse.core.runtime.NullProgressMonitor;

import ch.ethz.resynth.tree.Diff
import ch.ethz.resynth.tree.ProjectTree
import ch.ethz.resynth.tree.Refactor
import ch.ethz.resynth.tree.RefactorSearch
import ch.ethz.resynth.tree.RefactorSearchAStar
import ch.ethz.resynth.tree.ProjectTreeBuilder
import ch.ethz.resynth.eclipse.EclipseRefactoringSequenceExecutor

import rscore.dsl.entity.RSMethod

import scala.util.control.Breaks.{break, breakable}

object TemplateMethod {

    def formTemplateMethod(method1: RSMethod, method2: RSMethod): Unit = {
        val tree1: ProjectTree = new ProjectTree
		val tree2: ProjectTree = new ProjectTree
		tree1.setName("<METHODBODY>")
		tree2.setName("<METHODBODY>")
		ProjectTreeBuilder.generateProjectTreeFromASTNode(tree1, method1.getDeclaration)
		ProjectTreeBuilder.generateProjectTreeFromASTNode(tree2, method2.getDeclaration)
		
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
			
//					val seq = new EclipseRefactoringSequenceExecutor(new NullProgressMonitor());
//					val window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
//					val successfulFullSequence = seq.execute(window, refactorSteps);
//					
//					if (successfulFullSequence) {
//						return null
//					}
					numGlobalFailures += 1;
					//"Failed to run sequence "
				}
			}

		}catch{
		    case e =>
		        val n = e.toString()
				e.printStackTrace()
		}
    }
}