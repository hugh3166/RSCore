package rscore.dsl.entity.collection
import rscore.dsl.entity.RSEntity
import rscore.dsl.query.RSQuery
import rscore.dsl.query.NameQuery
import rscore.dsl.entity.RSField
import scala.collection.mutable.ArraySeq
import rscore.dsl.query.NameRegQuery
import rscore.dsl.query.ModifierQuery
import rscore.dsl.common.RSObject
import org.eclipse.jdt.core.IJavaElement
import scala.collection.mutable.ListBuffer
import rscore.dsl.traits.action.RSTPullUp
import rscore.dsl.traits.action.RSTMoveMember
import rscore.dsl.traits.action.RSTPushDownRefactoring
import org.jruby.RubyArray
import org.jruby.javasupport.JavaEmbedUtils
import org.jruby.Ruby

case class RSCollection[T <: RSEntity](rsElements: Array[T])
	extends RSObject
	with Iterable[T]

	// Action traits
	with RSTPullUp
	with RSTPushDownRefactoring
	with RSTMoveMember {

	override def iterator: Iterator[T] = {
		return rsElements.iterator
	}

	override val self = this

	// def this(elements: ArraySeq[T]) = this(elements.toArray[T])
	// val elements: Array[T]
	def all(): Array[T] = rsElements
	// def toTarget(): RSTarget
	def origin: Array[T] = rsElements
	def toArray: Array[T] = this.origin
	def first(): T = rsElements.head

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
	
	// TODO: <y> temporary methods: improve these methods
	def withPublic(): RSCollection[T] = {
		select(By.Modifier("public"))
	}
	def withoutPublic(): RSCollection[T] = {
		select(By.Modifier(With.out("public")))
	}
	def withPrivate(): RSCollection[T] = {
		select(By.Modifier("private"))
	}
	def withoutPrivate(): RSCollection[T] = {
		select(By.Modifier(With.out("private")))
	}
	def withFinal(): RSCollection[T] = {
		select(By.Modifier("public"))
	}
	def withoutFinal(): RSCollection[T] = {
		select(By.Modifier(With.out("final")))
	}
	def withStatic(): RSCollection[T] = {
		select(By.Modifier("static"))
	}
	def withoutStatic(): RSCollection[T] = {
		select(By.Modifier(With.out("static")))
	}

	// Just an alias to select
	def my_select(query: RSQuery): RSCollection[T] = {
		return this.select(query)
	}

	def toRuby: RubyArray = {
		JavaEmbedUtils.javaToRuby(Ruby.getGlobalRuntime(), rsElements).convertToArray()
	}
	def to_rb: RubyArray = this.toRuby		// just an alias
	
	/**
	 * select �̌��ʂ� RubyArray �ɕϊ�����
	 * TODO: ����C���܂������Ȃ�
	 */
	def Select(query: RSQuery): RubyArray = {
		return this.select(query).toRuby
	}

	/**
	 * �ŏ���1�������o��
	 * EntityNotFoundException ���E����������Ȃ�
	 */
	def selectOne(query: RSQuery): T = {
		val result = query.executeOne(this)
		return result
	}
	def Select_one(query: RSQuery): T = this.selectOne(query) // just an alias
	def select_one(query: RSQuery): T = this.selectOne(query) // just an alias

}

