package dsl.entity.collection
import dsl.common.RSParams
import dsl.common.RSParam

/**
 * where() ���̌������ł���悤�ɂ���g���C�g
 * �^�ϊ������܂������Ȃ��̂ŁC�����N���X�� toArray ��������
 */
trait Where[T]{
	val elements: Array[T]
	// def dispatchWhere(param: (String, Array[String])): Set[T]
	// def dispatchWhereNot(param: (String, Array[String])): Set[T]
	def dispatchWhere(param: RSParam[_]): Set[T]
	def dispatchWhereNot(param: RSParam[_]): Set[T]
 
 	def executeWhereQuery(params: Array[RSParam[_]]): Set[T] = {
		var result = elements.toSet[T]
		for (param <- params) {
			result = result & dispatchWhere(param)
		}
		// return result.toArray[T]
		return result
	}
	
	def executeWhereNotQuery(params: Array[RSParam[_]]): Set[T] = {
		var result = elements.toSet[T]
		//for (param <- params.getValue()) {
		for (param <- params) {
			result = result & dispatchWhereNot(param)
		}
		// return result.toArray[T]
		return result
	}
	

}