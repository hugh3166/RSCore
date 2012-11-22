package dsl.entity
import org.eclipse.jdt.core.IPackageFragment
import dsl.entity.collection.RSClasses

class RSPackage(val element: IPackageFragment) extends RSEntity{
	val __identifier:String = "package"
		
	/**
	 * �p�b�P�[�W�ȉ��Ő錾����Ă���N���X(IType)�����ׂĎ擾����
	 */
	def classes(includeNested: Boolean = false): Array[RSClass] = {
		var result = List[RSClass]()
		var cus = element.getCompilationUnits()
		if (includeNested) {
			for (cu <- cus) { result = result ::: cu.getAllTypes().map(e => new RSClass(e)).toList }
		} else {
			for (cu <- cus) { result = result ::: cu.getTypes().map(e => new RSClass(e)).toList }
		}
		return result.toArray
	}
	
	def classes_(includeNested: Boolean = false): RSClasses = {
		return new RSClasses(this.classes())
	}
	
	def classes(): Array[RSClass] = classes(true)
	def classes_(): RSClasses = classes_(true)
	
	override def origin() = element
}