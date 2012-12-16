package rscore.dsl.entity
import org.eclipse.jdt.core.ILocalVariable
import rscore.dsl.traits.search.NameBasedSearchable
import rscore.dsl.traits.search.SignatureBasedSearchable
import org.eclipse.jdt.core.Signature

/**
 * ���\�b�h�p�����[�^��\���N���X
 * @see ILocalVariables represents a local variable declared in a method or an initializer.
 */
class RSParameter(val element: ILocalVariable)
	extends RSEntity
	with NameBasedSearchable
	// with SignatureBasedSearchable
	{
	
	override val kind = RSEntity.PARAMETER
	override val __identifier = "parameter"

	// �ϐ���
	override val name: String = element.getElementName()
	// �V�O�l�`�� �� �^��
	// override val signature: Array[String] = Signature.getParameterTypes(element.getTypeSignature())

	override def origin() = element
}