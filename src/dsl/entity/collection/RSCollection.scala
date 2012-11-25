package dsl.entity.collection
import dsl.entity.RSEntity
import dsl.query.RSQuery
import dsl.query.NameQuery
import dsl.entity.RSField
import scala.collection.mutable.ArraySeq
import dsl.query.NameRegQuery
import dsl.query.ModifierQuery
import dsl.common.RSObject

case class RSCollection[T <: RSEntity](rsElements: Array[T]) extends RSObject with Iterable[T]{
	def iterator: Iterator[T] = {
		return rsElements.iterator
	}
	
	
	// def this(elements: ArraySeq[T]) = this(elements.toArray[T])
	// val elements: Array[T]
	def all(): Array[T] = rsElements
	// def toTarget(): RSTarget
	def origin: Array[T] = rsElements
	override def first(): T = rsElements.first
	
	// �R���N�V�����̗v�f�����擾����3�̊֐��i2�̓G�C���A�X�j
	def length: Int = rsElements.length
	override def size = length
	def count = length
	
	override def isEmpty: Boolean = this.length == 0
	def notFound: Boolean = isEmpty
	def isNotEmpty: Boolean = !isEmpty
	def found: Boolean = isNotEmpty
	
	def apply(index: Int): T = rsElements(index)

	/**
	 * �R���N�V��������N�G���Ƀ}�b�`����I�u�W�F�N�g����������
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

}

