package dsl.entity.collection

class Qualifier{}
/**
 * �����p�p�����[�^
 * �����̕��@�iAnd, Or, Not�j�����肷��C�Ƃ����Ӗ��� Qualifier�i����q�j�ƌĂ�
 */
case class With[T] (val values: T*) extends Qualifier{
	// MEMO: �P�[�X�N���X�̕⏕�R���X�g���N�^�� new ... �ł����ĂׂȂ�
	def this(values: Array[T]) = this(values:_*)
}
case class WithAnd[T] (val values: T*) extends Qualifier{}

case class Without[T](val values: T*) extends Qualifier{
	def this(value: Array[T]) = this(value:_*)
}

