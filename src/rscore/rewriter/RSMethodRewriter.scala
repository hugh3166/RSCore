package rscore.rewriter
import org.eclipse.jface.text.Document
import rscore.dsl.entity.RSMethod
import rscore.dsl.util.ASTUtil
import org.eclipse.jdt.core.dom.MethodDeclaration
import org.eclipse.jdt.internal.corext.refactoring.structure.ASTNodeSearchUtil
import org.eclipse.jdt.core.dom.SimpleName
import org.eclipse.jdt.core.dom.Name

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
	 * �ԋp�l�^��ύX����
	 * NOTE: ast.newSimpleName(str) �� str ���\����g�ݍ��݌^���ƁCIllegalArgumentException
	 */
	def changeReturnType(newReturnType: String): Unit = {
		val ast = this.cu.getAST()
		val simpleType = ast.newSimpleType(ast.newSimpleName(newReturnType))
		this.dec.setReturnType(simpleType)
	}
	
	/**
	 * ���O��ύX����
	 * ���t�@�N�^�����O�ł͂Ȃ��̂ŁC�Q�Ɗ֌W�͈�؃`�F�b�N���Ȃ�
	 */
	def changeName(newName: String): Unit = {
		val simpleName = this.cu.getAST().newSimpleName(newName)
		this.dec.setName(simpleName)
	}
}