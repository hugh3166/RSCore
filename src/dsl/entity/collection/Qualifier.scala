package dsl.entity.collection

class Qualifier {}
/**
 * �����p�p�����[�^
 * �����̕��@�iAnd, Or, Not�j�����肷��C�Ƃ����Ӗ��� Qualifier�i����q�j�ƌĂ�
 */
case class With[T](values: T*){
	
}
object With{
	def or[T](values: T*): WithOr[T] = new WithOr[T](values: _*)
	def and[T](values: T*): WithAnd[T] = new WithAnd[T](values: _*)
	def out[T](values: T*): Without[T] = new Without[T](values: _*)
}

class WithOr[T](val values: T*) extends Qualifier {}
class WithAnd[T](val values: T*) extends Qualifier {}
class Without[T](val values: T*) extends Qualifier {}

