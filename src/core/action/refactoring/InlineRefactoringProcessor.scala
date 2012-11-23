package core.action.refactoring
import dsl.common.RSObject
import dsl.entity.RSField
import org.eclipse.jdt.internal.corext.refactoring.code.InlineConstantRefactoring
import dsl.util.ASTUtil
import org.eclipse.jdt.core.dom.CompilationUnit
import core.helper.RefactoringHelper
import org.eclipse.jdt.internal.corext.refactoring.util.RefactoringASTParser
import org.eclipse.jdt.internal.ui.javaeditor.ASTProvider

class InlineRefactoringProcessor(rsObject: RSObject) extends AbstractRefactoringProcessor {
	override def createAction(): RSRefactoringAction = {
		rsObject match {
			case f: RSField => return createActionForField(f)
			case _ => return new RSRefactoringAction(Seq())
		}
	}

	private def createActionForField(f: RSField): RSRefactoringAction = {
		val action: (() => Unit) =
			() => {
				val origin = f.origin()
				val nameRange = origin.getNameRange()
				val offset = nameRange.getOffset()
				val length = nameRange.getLength()
				
				println("offset=" + offset)
				println("length=" + length)

				val cu = origin.getCompilationUnit()
				val node = new RefactoringASTParser(ASTProvider.SHARED_AST_LEVEL).parse(cu, true)
				
				val refactoring = new InlineConstantRefactoring(cu, node, offset, length)
				val status = RefactoringHelper.performRefactoring(refactoring)
				println("STATUS =======> " + status)

			}
		
		return new RSRefactoringAction(Seq(action))

	}

}