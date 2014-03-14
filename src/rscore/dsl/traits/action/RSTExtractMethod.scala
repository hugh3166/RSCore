package rscore.dsl.traits.action

import rscore.dsl.detail.RSDetailEntity
import rscore.action.refactoring.RSExtractMethodRefactoringProcessor

trait RSTExtractMethod extends RefactoringTrait{
	
	def extractMethod(name: String): Unit = {
		self match{
			case detail: RSDetailEntity =>{

				val processor = new RSExtractMethodRefactoringProcessor(detail, name)
				processor.createAction().perform()
			}
		}
	}

}