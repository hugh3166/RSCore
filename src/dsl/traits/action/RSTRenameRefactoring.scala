package dsl.traits.action
import dsl.entity.RSField
import core.action.refactoring.RenameFieldRefactoringProcessor
import dsl.entity.RSEntity

/**
 * ���O�̕ύX���t�@�N�^�����O��񋟂���g���C�g
 */
trait RSTRenameRefactoring extends RefactoringTrait{
	// val self: RSEntity
	def rename(newName: String): Unit = {
		self match{
			case f: RSField => RenameFieldRefactoringProcessor.createAction(self.asInstanceOf[RSField], newName).perform()
			// case m: RSMethod => RenameFieldRefactoringProcessor.createAction(self, newName).perform()	
		}
		
	}

}