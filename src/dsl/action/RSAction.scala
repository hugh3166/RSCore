package dsl.action

/**
 * �G���e�B�e�B�ɑ΂���A�N�V������\���N���X
 * Action has a processor ���f�����̗p���Ă���
 */
class RSAction(val processor: RSActionProcessor) {
	var params = Set[RSActionParam[_]]()
	def perform(): RSActionStatus = {
		var action = processor.createAction()
		return null
	}
	
	// Memo: Fluent interface
	def addParam(param: RSActionParam[_]): RSAction = {
		this.params += param
		return this
	}
}