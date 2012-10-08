package dsl.entity
import org.eclipse.jdt.core.IMethod
import org.eclipse.jdt.core.Signature
import org.eclipse.jdt.core.dom.MethodDeclaration
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import dsl.entity.collection.RSMethods
import org.eclipse.jdt.internal.corext.refactoring.structure.ASTNodeSearchUtil
import org.eclipse.jdt.core.dom.Modifier
import dsl.util.ASTUtil
import org.eclipse.jdt.core.dom.CompilationUnit
import org.eclipse.jdt.core.dom.Type

class RSMethod(val method: IMethod) extends Modifiable {
	// ����CIMethod �����ڗ~�����Ȃ������p
	def origin: IMethod = method

	def name(): String = {
		return method.getElementName()
	}
	
	def returnType: Type = {
		return this.declaration.asInstanceOf[MethodDeclaration].getReturnType2()
	}
	
	// �ԋp�l�^���w�肳�ꂽ���̂ƈ�v���邩�i�P�Ȃ閼�O��r�j
	def hasReturnTypeName(returnTypeName: String): Boolean = {
		return this.returnType.toString() == returnTypeName
	}
	
	def hasReturnTypeNamesOr(returnTypeNames: Array[String]): Boolean = {
		return returnTypeNames.exists(hasReturnTypeName(_))
	}

	override def getDeclaration(): MethodDeclaration = {
		var cu = ASTUtil.createAST(method.getCompilationUnit()).asInstanceOf[CompilationUnit]
		var dec: MethodDeclaration = ASTNodeSearchUtil.getMethodDeclarationNode(method, cu)
		return dec
	}
	

	def signatures(): Array[String] = {
		return Signature.getParameterTypes(method.getSignature())
	}

	def ast(): MethodDeclaration = {
		throw new NotImplementedException
	}

}

object RSMethod {
	implicit def convertToRSMethod(method: IMethod): RSMethod = new RSMethod(method)

	def create(method: IMethod): RSMethod = {
		return method.asInstanceOf[RSMethod]
	}
}