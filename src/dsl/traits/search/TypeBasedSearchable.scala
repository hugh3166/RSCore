package dsl.traits.search
import org.eclipse.jdt.core.dom.Type

/**
 * �^�ɂ���Č������邽�߂̃g���C�g
 */
trait TypeBasedSearchable {
	val typ: Type
	
	// �^���w�肳�ꂽ���̂ƈ�v���邩�i�P�Ȃ閼�O��r�j
	def hasTypeName(returnTypeName: String): Boolean = {
		return this.typ.toString() == returnTypeName
	}
	
	// �w�肳�ꂽ���O�̂����C�ǂꂩ�������Ă������
	def hasTypeNamesOr(typeNames: Array[String]): Boolean = {
		return typeNames.exists(hasTypeName(_))
	}
}