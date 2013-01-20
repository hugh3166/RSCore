package rscore.dsl.query
import rscore.dsl.traits.search.RSSearchTrait
import rscore.dsl.entity.RSEntity
import rscore.dsl.entity.collection.Qualifier
import rscore.dsl.entity.collection.RSCollection
import rscore.dsl.exception.EntityNotFoundException

abstract class RSQuery(val qualfier: Qualifier) {
	// to be overridden in subclasses
	def execute[T <: RSEntity](collection: RSCollection[T]): RSCollection[T]

	/**
	 * �擪�̗v�f1�������o���i�v�f�����炩��1���Ƃ킩��悤�ȂƂ��Ɏg���j
	 * TODO: ���́CTemplateMethod �p�^�[�������C�p�t�H�[�}���X�����̂ł�������
	 */
	def executeOne[T <: RSEntity](collection: RSCollection[T]): T = {
		val r = this.execute(collection)
		if(!r.found){
			throw new EntityNotFoundException
		}
		return r.first
	}
}
