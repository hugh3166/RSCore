package dsl.entity
import org.eclipse.jdt.core.IJavaElement
import dsl.common.RSObject

abstract class RSEntity extends RSObject{
	// �e�^�̎��ʎq�����Ă����i���ƂŁCisInstanceOf[] ���Ȃ��čςނ悤�Ɂj
	val __identifier: String
	
	// ���̂�Ԃ�
	def origin(): IJavaElement
}
