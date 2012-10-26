package dsl.entity.collection
import dsl.query.NameQuery
import scala.util.matching.Regex
import dsl.query.NameRegQuery
import dsl.query.ModifierQuery
import dsl.query.ReturnTypeQuery
import dsl.query.CallbackQuery
import dsl.entity.RSEntity

object By {
	/*
	 * find(By.***()) �� *** �̕����ɑ������镶����i���\�b�h���j�ƁC�Ή�����N�G���I�u�W�F�N�g(**Query)���Ђ��Â���
	 * RSQuery �̃t�@�N�g���i�ƌ��邱�Ƃ��ł���j
	 */
	def name(q: Qualifier): NameQuery = NameQuery(q)
	def namereg(q: Qualifier): NameRegQuery = NameRegQuery(q)
	def modifier(q: Qualifier): ModifierQuery = ModifierQuery(q)
	def returnType(q: Qualifier): ReturnTypeQuery = ReturnTypeQuery(q)
	def callback[T <: RSEntity](q: Qualifier): CallbackQuery[T] = CallbackQuery[T](q)
}