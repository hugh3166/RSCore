package dsl.entity
import org.eclipse.jdt.core.IJavaElement

trait RSEntity[T] {
	// �e�^�̎��ʎq�����Ă����i���ƂŁCisInstanceOf[] ���Ȃ��čςނ悤�Ɂj
	val __identifier: String
	
	
	val element: T
	// ���̂�Ԃ�
	def origin(): T = return element
	
	// def toTarget(): RSTarget
	
}
