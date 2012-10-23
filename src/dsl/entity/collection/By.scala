package dsl.entity.collection
import dsl.query.NameQuery
import scala.util.matching.Regex
import dsl.query.NameRegQuery
import dsl.query.ModifierQuery
import dsl.query.ReturnTypeQuery

object By {
	/*
	 * find(By.***()) �� *** �̕����ɑ������镶����i���\�b�h���j�ƁC�Ή�����N�G���I�u�W�F�N�g(**Query)���Ђ��Â���
	 */
	def name(names: String*): NameQuery = NameQuery(names.toArray)
	def namereg(nameregs: String*): NameRegQuery = NameRegQuery(nameregs.toArray)
	def modifier(modifiers: String*): ModifierQuery = ModifierQuery(modifiers.toArray)
	def returnType(returnTypes: String*): ReturnTypeQuery = ReturnTypeQuery(returnTypes.toArray)
}