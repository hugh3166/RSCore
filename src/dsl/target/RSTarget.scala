package dsl.target
import dsl.entity.RSEntity
import dsl.entity.RSWorkspace

/**
 * �A�N�V�����̃^�[�Q�b�g�ɂȂ�G���e�B�e�B
 * �i�����CRSEntity �̏W���i�̃��b�v�j�j
 * target.action(Processor).perform
 * fields.toTarget.action("refactoring", "rename").param(...).param(...).......perform()
 */
class RSTarget(val id: String, val entity: RSEntity, val parameters: (String, String)){
	// �p�����[�^���v��Ȃ��ꍇ
	def this(id: String, entity: RSEntity) = this(id, entity, null) 
	
	// id ���炢��Ȃ��ꍇ
	def this(entity: RSEntity) = this("", entity, null)
}