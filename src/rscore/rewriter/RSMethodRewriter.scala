package rscore.rewriter
import org.eclipse.jface.text.Document
import rscore.dsl.entity.RSMethod
import rscore.dsl.util.ASTUtil
import org.eclipse.jdt.core.dom.MethodDeclaration
import org.eclipse.jdt.internal.corext.refactoring.structure.ASTNodeSearchUtil
import org.eclipse.jdt.core.dom.SimpleName

/**
 * RSMethod �ɑ����v���p�e�B������������i���t�@�N�^�����O�ł͂Ȃ��jRewriter
 */
class RSMethodRewriter(m: RSMethod) extends AbstractRewriter(m){
	override val dec: MethodDeclaration = ASTNodeSearchUtil.getMethodDeclarationNode(m.origin, cu)

	/**
	 * �A�N�Z�X�C���q��ύX����
	 * TODO: �����񂩂�̕ϊ��C�����w��
	 */
	def changeModifier(newModifier: Int): Unit = {
		this.dec.setModifiers(newModifier)
	}
	
	/**
	 * ���O��ύX����
	 * ���t�@�N�^�����O�ł͂Ȃ��̂ŁC�Q�Ɗ֌W�͈�؃`�F�b�N���Ȃ�
	 */
	def changeName(newName: String): Unit = {
		this.dec.setName(this.cu.getAST().newSimpleName(newName))
	}
}