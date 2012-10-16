package dsl.entity.collection
import dsl.common.RSParam

/**
 * where() ���̌������ł���悤�ɂ���g���C�g
 * @see http://www.scala-lang.org/docu/files/collections-api/collections_38.html
 */
trait Where[T]{
	val elements: Array[T]
	
	// -- To be overridden in subclasses
	def dispatchWhere(param: RSParam[_]): Set[T]
	
	def where(params: Array[RSParam[_]]): Array[T]
	def where(params: RSParam[_]*): Array[T] = where(params.toArray)
	
	/**
	 * ���������ŏ��̗v�f�����Ԃ�
	 * TODO: �A���S���Y���C��
	 */
	def find(params: Array[RSParam[_]]): T = where(params).first
	def find(params: RSParam[_]*): T = find(params.toArray)
	
 
 	def executeWhereQuery(params: Array[RSParam[_]]): Set[T] = {
		var result = elements.toSet[T]
		for (param <- params) {
			result = result & dispatchWhere(param)
		}
		// return result.toArray[T]
		return result
	}
	
	

}