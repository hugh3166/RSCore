package rscore.dsl.entity.collection
import scala.util.matching.Regex
import rscore.dsl.query.NameQuery
import rscore.dsl.query.NameRegQuery
import rscore.dsl.query.ModifierQuery
import rscore.dsl.query.CallbackQuery
import rscore.dsl.entity.RSEntity
import rscore.dsl.query.TypeQuery

object By {
	/*
	 * find(By.***()) �� *** �̕����ɑ������镶����i���\�b�h���j�ƁC�Ή�����N�G���I�u�W�F�N�g(**Query)���Ђ��Â���
	 * RSQuery �̃t�@�N�g���i�ƌ��邱�Ƃ��ł���j
	 * �������ЂƂ����w�肵�Ȃ��Ƃ��́CBy.Name("aaaa") �̂悤�ɂ�������
	 */
	def Name(q: Qualifier): NameQuery = NameQuery(q)
	def Name(name: String): NameQuery = NameQuery(With.or(Array(name)))
	// just an alias
	def name(q: Qualifier): NameQuery = NameQuery(q)
	def name(name: String): NameQuery = NameQuery(With.or(Array(name)))

	def Namereg(q: Qualifier): NameRegQuery = NameRegQuery(q)
	def Namereg(namereg: String): NameRegQuery = NameRegQuery(With.or(Array(namereg)))
	// just an alias
	def namereg(q: Qualifier): NameRegQuery = NameRegQuery(q)
	def namereg(namereg: String): NameRegQuery = NameRegQuery(With.or(Array(namereg)))
	
	def Modifier(q: Qualifier): ModifierQuery = ModifierQuery(q)
	def Modifier(modifier: String): ModifierQuery = ModifierQuery(With.or(Array(modifier)))
	// just an alias
	def modifier(q: Qualifier): ModifierQuery = ModifierQuery(q)
	def modifier(modifier: String): ModifierQuery = ModifierQuery(With.or(Array(modifier)))
	
	def Type(q: Qualifier): TypeQuery = TypeQuery(q)
	def Type(typeName: String): TypeQuery = TypeQuery(With.or(Array(typeName)))
	// just an alias
	def typename(q: Qualifier): TypeQuery = TypeQuery(q)
	def typename(typeName: String): TypeQuery = TypeQuery(With.or(Array(typeName)))
	
	// def Callback[T <: RSEntity](q: Qualifier): CallbackQuery[T] = CallbackQuery[T](Array(q))
	// def Callback[T <: RSEntity](callback: T => Boolean): CallbackQuery[T] = CallbackQuery[T](With.or(callback))
}