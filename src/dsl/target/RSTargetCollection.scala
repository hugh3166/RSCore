package dsl.target
import core.action.refactoring.EncapsulateSelfFieldRefactoringProcessor

/**
 * RSTarget �̏W���i�������ꂾ���j
 */
class RSTargetCollection(val targets: Array[RSTarget]) {
	def this(targets: RSTarget*) = this(targets.toArray[RSTarget])
	
	/**
	 * TODO: ��������Ȃ��̂ŏ���
	 */
	@Deprecated
	def toArray() : Array[RSTarget] = {
		return targets
	}
	
	def perform(actionId: String): Unit = {
		actionId match {
			case "sef" => new EncapsulateSelfFieldRefactoringProcessor(this).createAction().perform()
		}
	}

}