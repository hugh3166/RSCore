package dsl.entity
import org.eclipse.jdt.core.IJavaElement
import dsl.common.RSObject
import dsl.traits.action.RSTDelete

abstract class RSEntity extends RSObject{
	val self = this
	// �e�^�̎��ʎq�����Ă����i���ƂŁCisInstanceOf[] ���Ȃ��čςނ悤�Ɂj
	val __identifier: String
	
	// ���̂�Ԃ�
	def origin(): IJavaElement
}
