package rscore.util

/**
 * ������u���Ɋւ���w���p
 */
object StringHelper {
	/**
	 * �^����ꂽ�y�A�i�u�����C�u���敶����̃y�A�j�Ɋ�āC���ꂼ�� String#replaceAll(from, to) ���s��
	 */
	def replaceAllChain(string: String, pairs : Array[(String, String)]): String = {
		def replace(string: String, pair: (String, String)): String = {
			val from = pair._1
			val to = pair._2
			
			string.replaceAll(from, to)
		}
		val result = pairs.foldLeft(string)(replace)
		return result
	}

}