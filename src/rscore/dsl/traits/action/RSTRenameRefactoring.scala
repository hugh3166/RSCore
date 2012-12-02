package rscore.dsl.traits.action
import rscore.dsl.entity.RSField
import rscore.action.refactoring.RenameFieldRefactoringProcessor
import rscore.dsl.entity.RSEntity
import rscore.dsl.entity.RSMethod
import rscore.action.refactoring.RenameMethodRefactoringProcessor

/**
 * ���O�̕ύX���t�@�N�^�����O��񋟂���g���C�g
 */
trait RSTRenameRefactoring extends RefactoringTrait {
	// val self: RSEntity
	def rename(newName: String): Unit = {
		self match {
			case f: RSField => {
				val processor = new RenameFieldRefactoringProcessor(self.asInstanceOf[RSField], newName)
				processor.createAction().perform()
			}
			case m: RSMethod => {
				val processor = new RenameMethodRefactoringProcessor(m, newName)
				processor.createAction().perform()
			}
		}
		println("rename refactoring is completed")
	}

}