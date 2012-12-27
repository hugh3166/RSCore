package rscore.dsl.entity.collection
import rscore.dsl.entity.RSEntity
import rscore.dsl.query.RSQuery
import rscore.dsl.query.NameQuery
import rscore.dsl.entity.RSField
import scala.collection.mutable.ArraySeq
import rscore.dsl.query.NameRegQuery
import rscore.dsl.query.ModifierQuery
import rscore.dsl.common.RSObject
import org.eclipse.jdt.core.IJavaElement
import scala.collection.mutable.ListBuffer
import rscore.dsl.traits.action.RSTPullUp
import rscore.dsl.traits.action.RSTMoveMember

case class RSCollection[T <: RSEntity](rsElements: Array[T])
	extends RSObject
	with Iterable[T]
	with RSTPullUp
	with RSTMoveMember{
	
	override def iterator: Iterator[T] = {
		return rsElements.iterator
	}
	
	override val self = this

	// def this(elements: ArraySeq[T]) = this(elements.toArray[T])
	// val elements: Array[T]
	def all(): Array[T] = rsElements
	// def toTarget(): RSTarget
	def origin: Array[T] = rsElements
	def toArray: Array[T] = this.origin

	override def first(): T = rsElements.first

	// コレクションの要素数を取得する3つの関数（2つはエイリアス）
	def length: Int = rsElements.length
	override def size = length
	def count = length

	override def isEmpty: Boolean = this.length == 0
	def notFound: Boolean = isEmpty
	def isNotEmpty: Boolean = !isEmpty
	def found: Boolean = isNotEmpty

	def apply(index: Int): T = rsElements(index)

	/**
	 * コレクションからクエリにマッチするオブジェクトを検索する
	 */
	def select(query: RSQuery): RSCollection[T] = {
		var result = query.execute(this)
		return result
		// val a = abstractArray.map(_.asInstanceOf[T])
	}

	// Just an alias to select
	def my_select(query: RSQuery): RSCollection[T] = {
		return this.select(query)
	}
	// Just an alias to select (avoid conflict ruby-embedded Enumerable#select)
	def Select(query: RSQuery): RSCollection[T] = {
		return this.select(query)
	}

}

