package dsl.entity.collection
import dsl.entity.RSEntity
import dsl.common.RSParam

abstract class RSCollection(val elements: Array[_ <: RSEntity]) {
	def where(params: Array[RSParam[_]]): Array[_ <: RSEntity]
	def where(params: RSParam[_]*): Array[_ <: RSEntity] = {
		return where(params.toArray)
	}
	
	def whereNot(params: Array[RSParam[_]]): Array[_ <: RSEntity]
	def whereNot(params: RSParam[_]*): Array[_ <: RSEntity] = {
		return whereNot(params.toArray)
	}
	
	// 検索して見つかった最初の要素だけ返却する
	def find(params: Array[RSParam[_]]) : RSEntity = {
		return where(params).first
	}
	def find(params: RSParam[_]*) : RSEntity = {
		return find(params.toArray)
	}

}