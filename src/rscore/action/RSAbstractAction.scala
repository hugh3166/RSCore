package rscore.action

class RSAbstractAction(changes: Seq[() => Unit]) {
	/**
	 * �w�肳�ꂽ���t�@�N�^�����O�A�N�V�������������s����
	 * LTK �����ׂă��b�v���邽��
	 */
	def perform(): Boolean = {
		for (change <- changes) {
			change.apply()
		}
		return true
	}

}