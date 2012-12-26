package rscore.dsl.entity
import org.eclipse.jdt.core.IPackageFragment
import rscore.dsl.entity.collection.RSCollection

class RSPackage(val element: IPackageFragment, val parent: RSProject) extends RSEntity{
	override val kind = RSEntity.PACKAGE
	val __identifier:String = "package"
		
	/**
	 * �p�b�P�[�W�ȉ��Ő錾����Ă���N���X(IType)�����ׂĎ擾����
	 */
	private def getClasses(includeNested: Boolean): Array[RSClass] = {
		var result = List[RSClass]()
		var cus = element.getCompilationUnits()
		if (includeNested) {
			for (cu <- cus) { result = result ::: cu.getAllTypes().map(e => new RSClass(e, this)).toList }
		} else {
			for (cu <- cus) { result = result ::: cu.getTypes().map(e => new RSClass(e, this)).toList }
		}
		return result.toArray
	}
	
	def classes(includeNested: Boolean = false): RSCollection[RSClass] = {
		return new RSCollection[RSClass](getClasses(includeNested))
	}
	def classes(): RSCollection[RSClass] = classes(true)
	
	override def origin() = element
}