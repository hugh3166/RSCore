package dsl.action
import dsl.entity.RSEntity
import dsl.entity.RSWorkspace

/**
 * �A�N�V�����̃^�[�Q�b�g�ɂȂ�G���e�B�e�B
 * �i�����CRSEntity �̏W���i�̃��b�v�j�j
 * target.action(Processor).perform
 * fields.toTarget.action("refactoring", "rename").param(...).param(...).......perform()
 */
class RSTarget(val entities: Array[RSEntity[_]]){
	def action(kind: String, id: String): RSAction = {
		return null
	}
}