package dsl.entity
import org.eclipse.jdt.core.IJavaElement

trait RSEntity[T] {
	val element: T
	
	// ���̂�Ԃ�
	def origin(): T = {
		return element
	}
}