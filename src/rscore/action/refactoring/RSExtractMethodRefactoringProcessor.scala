package rscore.action.refactoring

import rscore.dsl.detail.RSDetailEntity
import rscore.dsl.entity.RSMethod
import rscore.helper.RefactoringHelper
import rscore.helper.RSRefactoringResult
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.jdt.internal.corext.refactoring.code.ExtractMethodRefactoring
import org.eclipse.jdt.core.dom.ASTNode

import org.eclipse.jdt.core.dom.Modifier

class RSExtractMethodRefactoringProcessor(detail: RSDetailEntity, name: String) extends RSAbstractRefactoringProcessor {

	// TODO: <y> method name, visibility and other property should be customizable, and use default value
	def createAction(): RSRefactoringAction = {
		detail.parent match {
			case m: RSMethod => createActionForMethod(m)
			case _ => return new RSRefactoringAction(Seq())
		}

	}
	
	private def createActionForMethod(m: RSMethod): RSRefactoringAction = {
		val action: (() => Unit) = () => {
			val iCU = m.origin.getCompilationUnit()
			
			val start = detail.startPos
			val len = detail.length

			val refactor = new ExtractMethodRefactoring(iCU, start, len)
			refactor.setVisibility(Modifier.PROTECTED)
			refactor.setMethodName(name)
			
			var pm = new NullProgressMonitor
			var initialStatus = refactor.checkInitialConditions(pm)
			var finalStatus = refactor.checkFinalConditions(pm)
			if (!initialStatus.isOK() || !finalStatus.isOK()) {
				throw new Exception("Condition checking exception")
			}
			
			val result: RSRefactoringResult = RefactoringHelper.performRefactoring(refactor)
		}
		return new RSRefactoringAction(Seq(action))
	}
}