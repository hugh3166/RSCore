package rscore.dsl.common

case class RSParam[T](val value: (String, Array[T])) {
	def and(other: RSParam[_]): Array[RSParam[_]] = {
		return Array[RSParam[_]](this, other)
	}
}

object RSParam {
	/**
	 * RSParam �P�̂ł��z�񈵂��ł���悤�ɂ���
	 */
	implicit def convertToArray(rsParam: RSParam[_]): Array[RSParam[_]] = {
		return Array(rsParam)
	}

	/**
	 * Array[RSParam[_]] �ɑ΂��āCand ���\�b�h���Ăׂ�悤�ɂ���
	 * �����I�ɂ�1�� Set �ɖ߂��āC�����ɗv�f��˂�����ŁC�z��ɒ���
	 */
	implicit def extendRSParamArray(rsParams: Array[RSParam[_]]) = new {
		def and(other: RSParam[_]): Array[RSParam[_]] = {
			return (rsParams.toSet + other).toArray
		}
	}
}