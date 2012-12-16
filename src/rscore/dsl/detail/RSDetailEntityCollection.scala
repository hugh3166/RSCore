package rscore.dsl.detail

/**
 * DetailEntity (Body �ȉ��̗v�f)�̏W����\���N���X�̊��N���X
 * ��{�I�ȃ��X�g�A�N�Z�b�T�Ȃǂ����b�v�������\�b�h��񋟂���
 */
case class RSDetailEntityCollection[T <: RSDetailEntity](elements: List[T]) {
	def length: Int = elements.length
	def size: Int = this.length
	def cont: Int = this.length
	
	def first = elements.first
	def apply(index: Int) = elements(index)
	
}