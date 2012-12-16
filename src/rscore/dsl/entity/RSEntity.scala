package rscore.dsl.entity
import org.eclipse.jdt.core.IJavaElement
import rscore.dsl.common.RSObject
import rscore.dsl.traits.action.RSTDelete

abstract class RSEntity extends RSObject{
	val kind: RSEntity.Value 
	val self = this
	
	// �e�^�̎��ʎq�����Ă����i���ƂŁCisInstanceOf[] ���Ȃ��čςނ悤�Ɂj
	// TODO: remove
	val __identifier: String
	
	// ���̂�Ԃ�
	def origin(): IJavaElement
}

object RSEntity extends Enumeration{
	val CLASS, FIELD, MEMBER, METHOD, PACKAGE, PARAMETER, PROJECT, TYPE, WORKSPACE = Value
}
