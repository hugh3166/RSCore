package dsl.entity.collection
import dsl.common.RSParams

/**
 * where() ���̌������ł���悤�ɂ���g���C�g
 * �^�ϊ������܂������Ȃ��̂ŁC�����N���X�� toArray ��������
 */
trait Where[T]{
	val elements: Array[T]
	def dispatch(param: (String, Array[String])): Set[T]
 
 	def executeWhereQuery(params: RSParams): Set[T] = {
		var result = elements.toSet[T]
		for (param <- params.getValue()) {
			result = result & dispatch(param)
		}
		// return result.toArray[T]
		return result
	}
	

}