package dsl.search_trait
import org.eclipse.jdt.core.dom.Type

trait ReturnTypeSearchable {
	val returnType: Type
	
	// �ԋp�l�^���w�肳�ꂽ���̂ƈ�v���邩�i�P�Ȃ閼�O��r�j
	def hasReturnTypeName(returnTypeName: String): Boolean = {
		return this.returnType.toString() == returnTypeName
	}
	
	// �w�肳�ꂽ���O�̂����C�ǂꂩ�������Ă������
	def hasReturnTypeNamesOr(returnTypeNames: Array[String]): Boolean = {
		return returnTypeNames.exists(hasReturnTypeName(_))
	}
	

}