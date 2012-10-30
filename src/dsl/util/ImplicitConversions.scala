package dsl.util
import org.eclipse.jdt.core.IMethod
import dsl.entity.RSMethod
import dsl.entity.collection.RSMethods
import dsl.entity.RSField
import org.eclipse.jdt.core.IField
import dsl.entity.collection.RSFields
import org.eclipse.jdt.core.IType
import dsl.entity.RSClass
import dsl.entity.collection.RSClasses
import org.eclipse.jdt.core.ILocalVariable
import dsl.entity.RSParameter
import dsl.entity.collection.RSParameters
import scala.collection.mutable.ArraySeq

/**
 * ImplicitConversion ���邽�߂̕ϊ����\�b�h�Q
 * NOTE: �ǂ����@�ł͂Ȃ����CArray[RS*] �ƁCArraySeq[RS*] ���i�قځj��ʂȂ�������D
 * Java ���C�u�������� Array �̕��������������C���Ă̂�����
 * TODO: Array �� ArraySeq �̑���ɂ��Ă͗v����
 */
object ImplicitConversions {
	implicit def convertToRSMethod(method: IMethod) = new RSMethod(method)
	implicit def convertToRSMethods(methods: Array[RSMethod]) = new RSMethods(methods)
	// implicit def convertToRSMethods(methods: ArraySeq[RSMethod]) = new RSMethods(methods.toArray)
	
	implicit def converToRSField(field: IField) = new RSField(field)
	implicit def converToRSFields(fields: Array[RSField]) = new RSFields(fields)
	// implicit def converToRSFields(fields: ArraySeq[RSField]) = new RSFields(fields.toArray)
	
	implicit def convertToRSClass(cls: IType) = new RSClass(cls)
	implicit def convertToRSClasses(clss: Array[RSClass]) = new RSClasses(clss)
	// implicit def convertToRSClasses(clss: ArraySeq[RSClass]) = new RSClasses(clss.toArray)
	
	implicit def converToRSParameter(prm: ILocalVariable) = new RSParameter(prm)
	implicit def convertTORSParameters(prms: Array[RSParameter]) = new RSParameters(prms)
	// implicit def convertTORSParameters(prms: ArraySeq[RSParameter]) = new RSParameters(prms.toArray)
}