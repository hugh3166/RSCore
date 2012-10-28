package core.action.refactoring
import org.eclipse.ltk.core.refactoring.Change
import org.eclipse.core.runtime.NullProgressMonitor

case class RSRefactoringAction(val changes: Seq[() => Unit]) {
	/**
	 * �w�肳�ꂽ���t�@�N�^�����O�A�N�V�������������s����
	 * LTK �����ׂă��b�v���邽��
	 */
	def perform() : Unit = {
		for(change <- changes){
			change()
		}
	}

}