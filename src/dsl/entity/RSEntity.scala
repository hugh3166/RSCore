package dsl.entity
import org.eclipse.jdt.core.IJavaElement

abstract class RSEntity{
	// �e�^�̎��ʎq�����Ă����i���ƂŁCisInstanceOf[] ���Ȃ��čςނ悤�Ɂj
	val __identifier: String
	
	// ���̂�Ԃ�
	def origin(): IJavaElement
}
