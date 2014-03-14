package rscore.action.refactoring
import rscore.action.RSAbstractActionProcessor

abstract class RSAbstractRefactoringProcessor extends RSAbstractActionProcessor{
	
	// TODO:<y> If need to implement more detail in this class?
	def createAction(): RSRefactoringAction
	
	private def checkCondition(): Boolean = {
		return true
	}
}