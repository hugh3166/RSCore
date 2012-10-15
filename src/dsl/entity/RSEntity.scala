package dsl.entity
import org.eclipse.jdt.core.IJavaElement

trait RSEntity[T] {
	val element: T
	
	// 実体を返す
	def origin(): T = {
		return element
	}
}