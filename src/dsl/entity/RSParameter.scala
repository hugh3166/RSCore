package dsl.entity
import org.eclipse.jdt.core.ILocalVariable
import dsl.search_trait.NameBasedSearchable

/**
 * ���\�b�h�p�����[�^��\���N���X
 * @see ILocalVariables represents a local variable declared in a method or an initializer.
 */
class RSParameter(val element: ILocalVariable)
	extends RSEntity[ILocalVariable]
	with NameBasedSearchable {
	
	override val __identifier = "parameter"

	// �ϐ���
	val name: String = element.getElementName()

	// �V�O�l�`�� �� �^��
	def signature: String = element.getTypeSignature()

	def hasSignature(signature: String): Boolean = {
		return this.signature == signature
	}
	
	def hasSignaturesOr(signatures: Array[String]): Boolean = {
		return signatures.exists(e => this.hasSignature(e))
	}
}