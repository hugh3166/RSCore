package dsl.entity.collection
import dsl.query.NameQuery
import scala.util.matching.Regex
import dsl.query.NameRegQuery
import dsl.query.ModifierQuery
import dsl.query.CallbackQuery
import dsl.entity.RSEntity
import dsl.query.TypeQuery

object By {
	/*
	 * find(By.***()) �� *** �̕����ɑ������镶����i���\�b�h���j�ƁC�Ή�����N�G���I�u�W�F�N�g(**Query)���Ђ��Â���
	 * RSQuery �̃t�@�N�g���i�ƌ��邱�Ƃ��ł���j
	 */
	def Name(q: Qualifier): NameQuery = NameQuery(q)
	def Namereg(q: Qualifier): NameRegQuery = NameRegQuery(q)
	def Modifier(q: Qualifier): ModifierQuery = ModifierQuery(q)
	def Type(q: Qualifier): TypeQuery = TypeQuery(q)
	def Callback[T <: RSEntity](q: Qualifier): CallbackQuery[T] = CallbackQuery[T](q)
}